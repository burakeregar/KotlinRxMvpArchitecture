package com.burakeregar.flights.app.di.scopes;

import javax.inject.Scope;
import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Dagger application scope
 */
@Scope
@Retention(RUNTIME)
public @interface PerApplication {
}
