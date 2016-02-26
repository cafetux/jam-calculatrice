package com.arolla.jam.calculatrice.infra.slack;

import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.impl.SlackSessionFactory;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by raphael_squelbut on 22/02/16.
 */
public class JamBotBuilder {

    private Function<String, String> producer;
    private Predicate<String> acceptor;
    private String writingChannel;
    private String listeningChannel;
    private SlackSession session;

    public JamBot build() {
        return new JamBot(session) {

            @Override
            public boolean itIsNotForMe(final String message) {
                return acceptor != null ?
                        !acceptor.test(message) : false;
            }

            @Override
            public String readingChannel() {
                return listeningChannel;
            }

            @Override
            public String writingChannel() {
                return writingChannel;
            }

            @Override
            public String produceResponse(final String message) {
                return producer.apply(message);
            }
        };
    }

    public JamBotBuilder transformWith(
            final Function<String, String> producer) {
        this.producer = producer;
        return this;
    }

    public JamBotBuilder acceptAccordingTo(
            final Predicate<String> acceptor) {
        this.acceptor = acceptor;
        return this;
    }

    public JamBotBuilder writeTo(final String channel) {
        this.writingChannel = channel;
        return this;
    }

    public JamBotBuilder listenTo(final String channel) {
        this.listeningChannel = channel;
        return this;
    }

    public JamBotBuilder withToken(final String token) {
        session = SlackSessionFactory
                .createWebSocketSlackSession(token);
        return this;
    }

    public JamBotBuilder withToken(Token bot) {
        session = SlackSessionFactory
                .createWebSocketSlackSession(bot.get());
        return this;
    }

}
