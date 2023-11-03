/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.commons.logging.impl;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

/**
 * Implementation of {@link Log} that maps directly to a
 * <strong>Logger</strong> for log4J version 1.2.
 * <p>
 * Initial configuration of the corresponding Logger instances should be done
 * in the usual manner, as outlined in the Log4J documentation.
 * <p>
 * The reason this logger is distinct from the 1.3 logger is that in version 1.2
 * of Log4J:
 * <ul>
 * <li>class Logger takes Priority parameters not Level parameters.
 * <li>class Level extends Priority
 * </ul>
 * Log4J1.3 is expected to change Level so it no longer extends Priority, which is
 * a non-binary-compatible change. The class generated by compiling this code against
 * log4j 1.2 will therefore not run against log4j 1.3.
 *
 * @deprecated Scheduled for removal since version 1.x of Log4j has reached end-of-life.
 */
public class Log4JLogger implements Log, Serializable {

    /** Serializable version identifier. */
    private static final long serialVersionUID = 5160705895411730424L;

    /** The fully qualified name of the Log4JLogger class. */
    private static final String FQCN = Log4JLogger.class.getName();

    private static final Priority traceLevel;

    static {
        if (!Priority.class.isAssignableFrom(Level.class)) {
            // nope, this is log4j 1.3, so force an ExceptionInInitializerError
            throw new InstantiationError("Log4J 1.2 not available");
        }

        // Releases of log4j1.2 >= 1.2.12 have Priority.TRACE available, earlier
        // versions do not. If TRACE is not available, then we have to map
        // calls to Log.trace(...) onto the DEBUG level.

        Priority _traceLevel;
        try {
            _traceLevel = (Priority) Level.class.getDeclaredField("TRACE").get(null);
        } catch (final Exception ex) {
            // ok, trace not available
            _traceLevel = Level.DEBUG;
        }
        traceLevel = _traceLevel;
    }

    /** Log to this logger */
    private transient volatile Logger logger;

    // ------------------------------------------------------------
    // Static Initializer.
    //
    // Note that this must come after the static variable declarations
    // otherwise initializer expressions associated with those variables
    // will override any settings done here.
    //
    // Verify that log4j is available, and that it is version 1.2.
    // If an ExceptionInInitializerError is generated, then LogFactoryImpl
    // will treat that as meaning that the appropriate underlying logging
    // library is just not present - if discovery is in progress then
    // discovery will continue.
    // ------------------------------------------------------------

    /** Logger name */
    private final String name;

    /**
     * Constructs a new instance.
     */
    public Log4JLogger() {
        name = null;
    }

    /**
     * For use with a log4j factory.
     *
     * @param logger Logger.
     */
    public Log4JLogger(final Logger logger) {
        if (logger == null) {
            throw new IllegalArgumentException(
                "Warning - null logger in constructor; possible log4j misconfiguration.");
        }
        this.name = logger.getName();
        this.logger = logger;
    }

    /**
     * Base constructor.
     *
     * @param name name.
     */
    public Log4JLogger(final String name) {
        this.name = name;
        this.logger = getLogger();
    }

    /**
     * Logs a message with {@code org.apache.log4j.Priority.DEBUG}.
     *
     * @param message to log
     * @see org.apache.commons.logging.Log#debug(Object)
     */
    @Override
    public void debug(final Object message) {
        getLogger().log(FQCN, Level.DEBUG, message, null);
    }

    /**
     * Logs a message with {@code org.apache.log4j.Priority.DEBUG}.
     *
     * @param message to log
     * @param t log this cause
     * @see org.apache.commons.logging.Log#debug(Object, Throwable)
     */
    @Override
    public void debug(final Object message, final Throwable t) {
        getLogger().log(FQCN, Level.DEBUG, message, t);
    }

    /**
     * Logs a message with {@code org.apache.log4j.Priority.ERROR}.
     *
     * @param message to log
     * @see org.apache.commons.logging.Log#error(Object)
     */
    @Override
    public void error(final Object message) {
        getLogger().log(FQCN, Level.ERROR, message, null);
    }

    /**
     * Logs a message with {@code org.apache.log4j.Priority.ERROR}.
     *
     * @param message to log
     * @param t log this cause
     * @see org.apache.commons.logging.Log#error(Object, Throwable)
     */
    @Override
    public void error(final Object message, final Throwable t) {
        getLogger().log(FQCN, Level.ERROR, message, t);
    }

    /**
     * Logs a message with {@code org.apache.log4j.Priority.FATAL}.
     *
     * @param message to log
     * @see org.apache.commons.logging.Log#fatal(Object)
     */
    @Override
    public void fatal(final Object message) {
        getLogger().log(FQCN, Level.FATAL, message, null);
    }

    /**
     * Logs a message with {@code org.apache.log4j.Priority.FATAL}.
     *
     * @param message to log
     * @param t log this cause
     * @see org.apache.commons.logging.Log#fatal(Object, Throwable)
     */
    @Override
    public void fatal(final Object message, final Throwable t) {
        getLogger().log(FQCN, Level.FATAL, message, t);
    }

    /**
     * Gets the native Logger instance we are using.
     *
     * @return the native Logger instance we are using.
     */
    public Logger getLogger() {
        Logger result = logger;
        if (result == null) {
            synchronized(this) {
                result = logger;
                if (result == null) {
                    logger = result = Logger.getLogger(name);
                }
            }
        }
        return result;
    }

    /**
     * Logs a message with {@code org.apache.log4j.Priority.INFO}.
     *
     * @param message to log
     * @see org.apache.commons.logging.Log#info(Object)
     */
    @Override
    public void info(final Object message) {
        getLogger().log(FQCN, Level.INFO, message, null);
    }

    /**
     * Logs a message with {@code org.apache.log4j.Priority.INFO}.
     *
     * @param message to log
     * @param t log this cause
     * @see org.apache.commons.logging.Log#info(Object, Throwable)
     */
    @Override
    public void info(final Object message, final Throwable t) {
        getLogger().log(FQCN, Level.INFO, message, t);
    }

    /**
     * Tests whether the Log4j Logger used is enabled for {@code DEBUG} priority.
     */
    @Override
    public boolean isDebugEnabled() {
        return getLogger().isDebugEnabled();
    }

    /**
     * Tests whether the Log4j Logger used is enabled for {@code ERROR} priority.
     */
    @Override
    public boolean isErrorEnabled() {
        return getLogger().isEnabledFor(Level.ERROR);
    }

    /**
     * Tests whether the Log4j Logger used is enabled for {@code FATAL} priority.
     */
    @Override
    public boolean isFatalEnabled() {
        return getLogger().isEnabledFor(Level.FATAL);
    }

    /**
     * Tests whether the Log4j Logger used is enabled for {@code INFO} priority.
     */
    @Override
    public boolean isInfoEnabled() {
        return getLogger().isInfoEnabled();
    }

    /**
     * Tests whether the Log4j Logger used is enabled for {@code TRACE} priority.
     * When using a log4j version that does not support the TRACE level, this call
     * will report whether {@code DEBUG} is enabled or not.
     */
    @Override
    public boolean isTraceEnabled() {
        return getLogger().isEnabledFor(traceLevel);
    }

    /**
     * Tests whether the Log4j Logger used is enabled for {@code WARN} priority.
     */
    @Override
    public boolean isWarnEnabled() {
        return getLogger().isEnabledFor(Level.WARN);
    }

    /**
     * Logs a message with {@code org.apache.log4j.Priority.TRACE}.
     * When using a log4j version that does not support the {@code TRACE}
     * level, the message will be logged at the {@code DEBUG} level.
     *
     * @param message to log
     * @see org.apache.commons.logging.Log#trace(Object)
     */
    @Override
    public void trace(final Object message) {
        getLogger().log(FQCN, traceLevel, message, null);
    }

    /**
     * Logs a message with {@code org.apache.log4j.Priority.TRACE}.
     * When using a log4j version that does not support the {@code TRACE}
     * level, the message will be logged at the {@code DEBUG} level.
     *
     * @param message to log
     * @param t log this cause
     * @see org.apache.commons.logging.Log#trace(Object, Throwable)
     */
    @Override
    public void trace(final Object message, final Throwable t) {
        getLogger().log(FQCN, traceLevel, message, t);
    }

    /**
     * Logs a message with {@code org.apache.log4j.Priority.WARN}.
     *
     * @param message to log
     * @see org.apache.commons.logging.Log#warn(Object)
     */
    @Override
    public void warn(final Object message) {
        getLogger().log(FQCN, Level.WARN, message, null);
    }

    /**
     * Logs a message with {@code org.apache.log4j.Priority.WARN}.
     *
     * @param message to log
     * @param t log this cause
     * @see org.apache.commons.logging.Log#warn(Object, Throwable)
     */
    @Override
    public void warn(final Object message, final Throwable t) {
        getLogger().log(FQCN, Level.WARN, message, t);
    }
}
