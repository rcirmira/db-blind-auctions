package com.db.system.backend.config.hk2;

import com.db.system.backend.resource.gateway.BackendResource;
import com.db.system.backend.resource.gateway.BackendResourceImpl;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

public class BackendServiceBinder extends AbstractBinder {
    @Override
    protected void configure() {
        // resources
        bind(BackendResourceImpl.class).to(BackendResource.class);
    }
}