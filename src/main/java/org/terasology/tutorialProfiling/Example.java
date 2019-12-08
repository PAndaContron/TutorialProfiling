/*
 * Copyright 2018 MovingBlocks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.terasology.tutorialProfiling;

import org.terasology.entitySystem.systems.BaseComponentSystem;
import org.terasology.entitySystem.systems.RegisterSystem;
import org.terasology.logic.console.commandSystem.annotations.Command;
import org.terasology.logic.console.commandSystem.annotations.CommandParam;

/**
 *  This class contains examples of performance mistakes and how to improve
 *  upon them. Each example is a single command which can be run in-game
 *  to test the performance of two different ways of accomplishing the same
 *  task, and compared using a profiler.
 */
@RegisterSystem
public class Example extends BaseComponentSystem {	
	/**
	 *  This command serves as an example/placeholder for how examples of
	 *  what to do and what not to do may be created further down the line.
	 *
	 *  An example for examples, so to speak.
	 */
	@Command( shortDescription = "Placeholder performance example", helpText = "Doesn't do anything at the moment" )
	public String testPerformancePlaceholder(@CommandParam( value = "Whether to use the efficient version or not" ) boolean efficient) {
		long time = System.currentTimeMillis();
		if(efficient) {
			// Do it the right way
		} else {
			// Do it the wrong way
		}
		time = System.currentTimeMillis() - time;
		return "That took "+time+"ms";
	}
}
