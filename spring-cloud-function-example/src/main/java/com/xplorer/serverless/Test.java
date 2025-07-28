package com.xplorer.serverless;

import java.util.function.Function;

public class Test implements Function<String, String> {
    @Override
    public String apply(String s) {
        return "Serverless programing demo: "+s;
    }
}
