// https://www.playframework.com/documentation/2.6.x/ScalaLogging

/**
 *  OFF     - Used to turn off logging, not as a message classification.
 *  ERROR   - Runtime errors, or unexpected conditions.
 *  WARN    - Use of deprecated APIs, poor use of API, ‘almost’ errors, other runtime situations 
              that are undesirable or unexpected, but not necessarily “wrong”.
 *  INFO    - Interesting runtime events such as application startup and shutdown.
 *  DEBUG   - Detailed information on the flow through the system.
 *  TRACE   - Most detailed information.
 */

import play.api.Logger

// The Logger object is your default logger and uses the name “application.”
Logger.debug("Attempting risky calculation.")

// 
// [debug] application - Attempting risky calculation.
// 



/**
 * Although it may be tempting to use the default logger everywhere, it’s generally a bad
 * design practice. Creating your own loggers with distinct names allows for flexible 
 * configuration, filtering of log output, and pinpointing the source of log messages.
 */

// You can create a new logger using the `Logger.apply` factory method with a name argument:
val accessLogger: Logger = Logger("access")

// A common strategy for logging application events is to use a distinct logger per class
// using the class name.
val logger: Logger = Logger(this.getClass())


/**
 * The SLF4J API has a concept of markers, which act to enrich logging messages and mark 
 * out messages as being of special interest.
 *
 * Markers are especially useful for triggering and filtering – for example, OnMarkerEvaluator can 
 * send an email when a marker is seen, or particular flows can be marked out to their own appenders.
 * 
 *
 * The Logger API provides access to markers through the play.api.MarkerContext trait.
 */

val marker: org.slf4j.Marker = MarkerFactory.getMarker("SOMEMARKER")
val mc: MarkerContext = MarkerContext(marker)

// You can also provide a typed MarkerContext by extending from `DefaultMarkerContext`.
val someMarker: org.slf4j.Marker = MarkerFactory.getMarker("SOMEMARKER")

case object SomeMarkerContext extends play.api.DefaultMarkerContext(someMarker)


// Once a `MarkerContext` has been created, it can be used with a logging statement,
// either explicitly:
logger.info("log message with explicit marker context with case object")(SomeMarkerContext)

// 
val otherMarker: Marker                 = MarkerFactory.getMarker("OTHER")
val otherMarkerContext: MarkerContext   = MarkerContext(otherMarker)

logger.info("log message with explicit marker context")(otherMarkerContext)

// or implicitly:
val marker: Marker                      = MarkerFactory.getMarker("MARKER")
implicit val mc: MarkerContext          = MarkerContext(marker)

logger.info("log message with explicit marker context")




