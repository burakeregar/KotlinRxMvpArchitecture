package com.burakeregar.flights.app.di.scopes;

import javax.inject.Scope;
import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Dagger activity scope. Used in activity modules to indicate that the scope of the injected object
 * is an activity.
 */
@Scope
@Retention(RUNTIME)
public @interface PerActivity {
}
