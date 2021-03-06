package com.artemis;

/**
 * Simple sequential invocation strategy.
 * @see SystemInvocationStrategy
 */
public class InvocationStrategy extends SystemInvocationStrategy {

	/** Processes all systems in order. */
	@Override
	protected void process() {
		Object[] systemsData = systems.getData();
		for (int i = 0, s = systems.size(); s > i; i++) {
			if (disabled.get(i))
				continue;

			updateEntityStates();
			((BaseSystem)systemsData[i]).process();
		}

		updateEntityStates();
	}
}
