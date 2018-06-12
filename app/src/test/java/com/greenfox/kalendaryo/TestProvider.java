package com.greenfox.kalendaryo;


import dagger.Module;
import dagger.Provides;

@Module
public class TestProvider {

    @Provides
    MockBackendApi provideMockBackendApi() {
        return new MockBackendApi();
    }
}
