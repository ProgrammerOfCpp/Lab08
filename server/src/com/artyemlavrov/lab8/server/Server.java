package com.artyemlavrov.lab8.server;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.*;
import java.util.function.Function;

public class Server {

    private final Function<Object, Serializable> getResponse;
    private final Thread thread;
    private final int port;
    private volatile boolean isRunning;
    private final Logger logger = (Logger) LoggerFactory.getLogger(Server.class);
    protected final ForkJoinPool requestExecutorPool;
    protected final ForkJoinPool responseSenderPool;
    protected final ExecutorService requestReaderPool;

    public Server(int port, Function<Object, Serializable> getResponse) {
        this.getResponse = getResponse;
        this.port = port;
        this.requestExecutorPool = ForkJoinPool.commonPool();
        this.responseSenderPool = ForkJoinPool.commonPool();
        this.requestReaderPool = Executors.newCachedThreadPool();

        thread = new Thread(() -> {
            try {
                Selector selector = createSelectorWithChannel();
                while (isRunning) {
                    try {
                        serverTick(selector);
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        });
    }

    public void start() {
        isRunning = true;
        thread.start();
    }

    private Selector createSelectorWithChannel() throws IOException {
        Selector selector = Selector.open();
        SocketAddress address = new InetSocketAddress(port);
        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.bind(address);
        channel.configureBlocking(false);
        channel.register(selector, SelectionKey.OP_ACCEPT);
        logger.info("Сервер запущен на порте {}", port);
        return selector;
    }

    private synchronized void serverTick(Selector selector) throws IOException {
        int readyChannels = selector.selectNow();
        if(readyChannels == 0) return;
        Set<SelectionKey> keys = selector.selectedKeys();
        for (Iterator<SelectionKey> it = keys.iterator(); it.hasNext(); ) {
            SelectionKey key = it.next();
            it.remove();
            if (key.isValid()) {
                processSelectionKey(key);
            }
        }
    }

    private void processSelectionKey(SelectionKey key) throws IOException {
        if (key.isAcceptable()) {
            onAcceptable(key);
        }
        if (key.isReadable()) {
            onReadable(key);
        }
    }

    private void onAcceptable(SelectionKey key) throws IOException {
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
        SocketChannel socketChannel = serverSocketChannel.accept();
        socketChannel.configureBlocking(false);
        socketChannel.register(key.selector(), SelectionKey.OP_READ);
        logger.info("Установлено соездинение с {}", socketChannel.getRemoteAddress());
    }

    private void onReadable(SelectionKey key) {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        readAndWriteChannel(socketChannel);
    }

    private void readAndWriteChannel(SocketChannel channel) {
        Callable<Object> readRequestTask = () -> {
            try {
                return readRequest(channel);
            } catch (IOException e) {
                closeChannel(channel);
                return null;
            }
        };
        Future<Object> requestFuture = requestReaderPool.submit(readRequestTask);
        Object rq = null;
        while (!requestFuture.isDone()) {
            try {
                rq = requestFuture.get();
            } catch (InterruptedException | ExecutionException ex) {
                logger.error(ex.getMessage());
                break;
            }
        }
        if (rq != null) {
            final Object request = rq;
            final Serializable resp = requestExecutorPool.invoke(new RecursiveTask<Serializable>() {
                @Override
                protected Serializable compute() {
                    return getResponse.apply(request);
                }
            });
            responseSenderPool.invoke(ForkJoinTask.adapt(() -> {
                try {
                    this.writeResponse(channel, resp);
                } catch (IOException ex) {
                    logger.error(ex.getMessage());
                    logger.error("Не удалось отправить ответ {}", channel.socket().getRemoteSocketAddress());
                }
            }));
        }
    }

    private Object readRequest(SocketChannel channel) throws IOException, ClassNotFoundException {
        ByteBuffer buffer = ByteBuffer.allocate(10000);
        buffer.clear();
        channel.read(buffer);
        buffer.flip();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buffer.array());
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        return objectInputStream.readObject();
    }

    private void writeResponse(SocketChannel channel, Serializable response) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(response);
        objectOutputStream.flush();
        byte[] bytes = byteArrayOutputStream.toByteArray();
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        channel.write(buffer);
    }

    private void closeChannel(SocketChannel channel) {
        try {
            logger.info("Закрыто соединение с {}", channel.getRemoteAddress());
            channel.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void stop() {
        isRunning = false;
    }
}
