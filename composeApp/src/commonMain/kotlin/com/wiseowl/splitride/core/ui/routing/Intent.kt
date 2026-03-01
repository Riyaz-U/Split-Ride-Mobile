package com.wiseowl.splitride.core.ui.routing

/**
 * Represents an intention to perform an action.
 *
 * @author Riyaz Uddin
 */
open class Intent()

/**
 * Represents a navigation action to a specific screen with optional parameters.
 *
 * @param screen The target screen to navigate to.
 * @param params Optional parameters to pass to the target screen.
 *
 * @author Riyaz Uddin
 */
class Navigation(val screen: Screen, val params: Map<String, Any> = emptyMap()): Intent()
class SnackBar(val text: String, val action: Intent? = null, val actionLabel: String? = null, val stateUpdater: StateUpdater? = null): Intent()
object CompletedOnboarding: Intent()