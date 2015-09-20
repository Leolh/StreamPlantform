package com.asiainfo.ocdp.streaming.common

import com.asiainfo.ocdp.streaming.constant.CommonConstant
import com.asiainfo.ocdp.streaming.tools.DateFormatUtils
import org.apache.log4j.PropertyConfigurator
import org.slf4j.{Logger, LoggerFactory}

/**
 * Created by leo on 9/8/15.
 */
trait Logging {

  PropertyConfigurator.configure(CommonConstant.log4jConfFile)

  // Make the log field transient so that objects with Logging can
  // be serialized and used on another machine
  @transient private var log_ : Logger = null

  // Method to get the logger name for this object
  protected def logName = {
    // Ignore trailing $'s in the class names for Scala objects
    this.getClass.getName.stripSuffix("$")
  }

  // Method to get or create the logger for this object
  protected def log: Logger = {
    if (log_ == null) {
      //      initializeIfNecessary()
      log_ = LoggerFactory.getLogger(logName)
    }
    log_
  }

  // Log methods that take only a String
  protected def logInfo(msg: => String) {
    if (log.isInfoEnabled) log.info(msg)
  }

  protected def logDebug(msg: => String) {
    if (log.isDebugEnabled) log.debug(msg)
  }

  protected def logTrace(msg: => String) {
    if (log.isTraceEnabled) log.trace(msg)
  }

  protected def logWarning(msg: => String) {
    if (log.isWarnEnabled) log.warn(msg)
  }

  protected def logError(msg: => String) {
    if (log.isErrorEnabled) log.error(msg)
  }

  // Log methods that take Throwables (Exceptions/Errors) too
  protected def logInfo(msg: => String, throwable: Throwable) {
    if (log.isInfoEnabled) log.info(msg, throwable)
  }

  protected def logDebug(msg: => String, throwable: Throwable) {
    if (log.isDebugEnabled) log.debug(msg, throwable)
  }

  protected def logTrace(msg: => String, throwable: Throwable) {
    if (log.isTraceEnabled) log.trace(msg, throwable)
  }

  protected def logWarning(msg: => String, throwable: Throwable) {
    if (log.isWarnEnabled) log.warn(msg, throwable)
  }

  protected def logError(msg: => String, throwable: Throwable) {
    if (log.isErrorEnabled) log.error(msg, throwable)
  }

  protected def logBegin {
    logInfo("= = " * 8 + "MainFrameManager Server start at " + DateFormatUtils.dateMs2Str(System.currentTimeMillis()) + "= = " * 8)
  }

  protected def logEnd {
    logInfo("= = " * 8 + "MainFrameManager Server stop at " + DateFormatUtils.dateMs2Str(System.currentTimeMillis()) + "= = " * 8)
  }
}