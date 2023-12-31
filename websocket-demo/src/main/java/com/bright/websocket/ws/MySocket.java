package com.bright.websocket.ws;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

/**
 * @see <a href="https://projects.eclipse.org/projects/ee4j.websocket">ee4j.websocket</a>
 * @see <a href="https://github.com/jakartaee/websocket">jakartaee/websocket</a>
 * @since 2023/8/7
 */
@Component
@ServerEndpoint("/hello/{uid}")
@Slf4j
public class MySocket {
    /**
     * Developers must implement this method to be notified when a new conversation has just begun.
     *
     * @param session the session that has just been activated.
     * @param config  the configuration used to configure this endpoint.
     */
    @OnOpen
    public void onOpen(Session session, EndpointConfig config, @PathParam("uid") String uid) {
        log.info("onOpen: uid = " + uid);
    }

    /**
     * This method is called immediately prior to the session with the remote peer being closed. It is called whether
     * the session is being closed because the remote peer initiated a close and sent a close frame, or whether the
     * local websocket container or this endpoint requests to close the session. The developer may take this last
     * opportunity to retrieve session attributes such as the ID, or any application data it holds before it becomes
     * unavailable after the completion of the method. Developers should not attempt to modify the session from within
     * this method, or send new messages from this call as the underlying connection will not be able to send them at
     * this stage.
     *
     * @param session     the session about to be closed.
     * @param closeReason the reason the session was closed.
     * @see <a href="https://github.com/jakartaee/websocket/blob/1.1.x/api/client/src/main/java/javax/websocket/OnClose.java">OnClose</a>
     */
    @OnClose
    public void onClose(Session session, CloseReason closeReason, @PathParam("uid") String uid) {
        log.info("onClose: uid = " + uid + ", closeReason = " + closeReason.getReasonPhrase());
    }


    /**
     * This method level annotation can be used to make a Java method receive incoming web socket messages. Each websocket
     * endpoint may only have one message handling method for each of the native websocket message formats: text, binary and
     * pong. Methods using this annotation are allowed to have parameters of types described below, otherwise the container
     * will generate an error at deployment time.
     * <p>
     * The allowed parameters are:
     * <ol>
     * <li>Exactly one of any of the following choices
     * <ul>
     * <li>if the method is handling text messages:
     * <ul>
     * <li>{@link java.lang.String} to receive the whole message</li>
     * <li>Java primitive or class equivalent to receive the whole message converted to that type</li>
     * <li>String and boolean pair to receive the message in parts</li>
     * <li>{@link java.io.Reader} to receive the whole message as a blocking stream</li>
     * <li>any object parameter for which the endpoint has a text decoder ({@link Decoder.Text} or
     * {@link Decoder.TextStream}).</li>
     * </ul>
     * </li>
     * <li>if the method is handling binary messages:
     * <ul>
     * <li>byte[] or {@link java.nio.ByteBuffer} to receive the whole message</li>
     * <li>byte[] and boolean pair, or {@link java.nio.ByteBuffer} and boolean pair to receive the message in parts</li>
     * <li>{@link java.io.InputStream} to receive the whole message as a blocking stream</li>
     * <li>any object parameter for which the endpoint has a binary decoder ({@link Decoder.Binary} or
     * {@link Decoder.BinaryStream}).</li>
     * </ul>
     * </li>
     * <li>if the method is handling pong messages:
     * <ul>
     * <li>{@link PongMessage} for handling pong messages</li>
     * </ul>
     * </li>
     * </ul>
     * </li>
     * <li>and Zero to n String or Java primitive parameters annotated with the {@code javax.websocket.server.PathParam}
     * annotation for server endpoints.</li>
     * <li>and an optional {@link Session} parameter</li>
     * </ol>
     * <p>
     * The parameters may be listed in any order.
     *
     * <p>
     * The method may have a non-void return type, in which case the web socket runtime must interpret this as a web socket
     * message to return to the peer. The allowed data types for this return type, other than void, are String, ByteBuffer,
     * byte[], any Java primitive or class equivalent, and anything for which there is an encoder. If the method uses a Java
     * primitive as a return value, the implementation must construct the text message to send using the standard Java
     * string representation of the Java primitive unless there developer provided encoder for the type configured for this
     * endpoint, in which case that encoder must be used. If the method uses a class equivalent of a Java primitive as a
     * return value, the implementation must construct the text message from the Java primitive equivalent as described
     * above.
     *
     * <p>
     * Developers should note that if developer closes the session during the invocation of a method with a return type, the
     * method will complete but the return value will not be delivered to the remote endpoint. The send failure will be
     * passed back into the endpoint's error handling method.
     *
     * <p>
     * For example:
     *
     * <pre>
     * <code>
     * &#64;OnMessage
     * public void processGreeting(String message, Session session) {
     *     System.out.println("Greeting received:" + message);
     * }
     * </code>
     * </pre>
     * <p>
     * For example:
     *
     * <pre>
     * <code>
     * &#64;OnMessage
     * public void processUpload(byte[] b, boolean last, Session session) {
     *     // process partial data here, which check on last to see if these is more on the way
     * }
     * </code>
     * </pre>
     * <p>
     * Developers should not continue to reference message objects of type {@link java.io.Reader},
     * {@link java.nio.ByteBuffer} or {@link java.io.InputStream} after the annotated method has completed, since they may
     * be recycled by the implementation.
     */
    @OnMessage
    public void processGreeting(String message, Session session, @PathParam("uid") String uid) {
        System.out.println(uid + ": Greeting received:" + message);
    }

    /**
     * Developers may implement this method when the web socket session creates some kind of error that is not modeled
     * in the web socket protocol. This may for example be a notification that an incoming message is too big to handle,
     * or that the incoming message could not be encoded.
     *
     * <p>
     * There are a number of categories of exception that this method is (currently) defined to handle:
     * <ul>
     * <li>connection problems, for example, a socket failure that occurs before the web socket connection can be
     * formally closed. These are modeled as {@link SessionException}s</li>
     * <li>runtime errors thrown by developer created message handlers calls.</li>
     * <li>conversion errors encoding incoming messages before any message handler has been called. These are modeled as
     * {@link DecodeException}s</li>
     * </ul>
     *
     * @param session the session in use when the error occurs.
     * @param thr     the throwable representing the problem.
     */
    @OnError
    public void onError(Session session, Throwable thr, @PathParam("uid") String uid) {
        log.error("onError: uid = " + uid, thr);
    }
}
