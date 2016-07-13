package com.github.diegopacheco.sandbox.java.uber.tchannel.main;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.uber.tchannel.api.TChannel;
import com.uber.tchannel.messages.JsonRequest;
import com.uber.tchannel.messages.JsonResponse;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

public class PingClient {

    private final String host;
    private final int port;
    private final int requests;

    public PingClient(String host, int port, int requests) {
        this.host = host;
        this.port = port;
        this.requests = requests;
    }

    public static void main(String[] args) throws Exception {
        Options options = new Options();
        options.addOption("h", "host", true, "Server Host to connect to");
        options.addOption("p", "port", true, "Server Port to connect to");
        options.addOption("n", "requests", true, "Number of requests to make");
        options.addOption("?", "help", false, "Usage");
        HelpFormatter formatter = new HelpFormatter();

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);

        if (cmd.hasOption("?")) {
            formatter.printHelp("PingClient", options, true);
            return;
        }

        String host = cmd.getOptionValue("h", "localhost");
        int port = Integer.parseInt(cmd.getOptionValue("p", "8888"));
        int requests = Integer.parseInt(cmd.getOptionValue("n", "10000"));

        System.out.println(String.format("Connecting from client to server on port: %d", port));
        new PingClient(host, port, requests).run();
        System.out.println("Stopping Client...");

    }

    public void run() throws Exception {
        TChannel tchannel = new TChannel.Builder("ping-client").build();

        Map<String, String> headers = new HashMap<String, String>() {
            {
                put("some", "header");
            }
        };

        JsonRequest<Ping> request = new JsonRequest.Builder<Ping>("some-service", "ping")
            .setBody(new Ping("{'key': 'ping?'}"))
            .setHeaders(headers)
            .build();

        for (int i = 0; i < this.requests; i++) {
            ListenableFuture<JsonResponse<Pong>> f = tchannel
            .makeSubChannel("ping-server").send(
                    request,
                    InetAddress.getByName(this.host),
                    this.port
                );

            final int iteration = i;
            Futures.addCallback(f, new FutureCallback<JsonResponse<Pong>>() {
                @Override
                public void onSuccess(JsonResponse<Pong> pongResponse) {
                    if (iteration % 1000 == 0) {
                        System.out.println(pongResponse);
                    }
                }

                @Override
                public void onFailure(Throwable throwable) {

                }
            });
        }

        tchannel.shutdown();

    }

}