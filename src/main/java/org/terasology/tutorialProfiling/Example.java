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
import org.terasology.math.geom.Vector3i;

/**
 *  This class contains examples of performance mistakes and how to improve
 *  upon them. Each example is a single command which can be run in-game
 *  to test the performance of two different ways of accomplishing the same
 *  task, and compared using a profiler.
 */
@RegisterSystem
public class Example extends BaseComponentSystem {	
	/**
	 *  This example shows 2 ways to iterate over vectors. The wrong way
	 *  involves creating a new Vector3i every iteration, while the right
	 *  way reuses the same object.
	 *
	 *  It's important to note that in some cases, using the same object
	 *  multiple times may not produce the desired behavior in cases where
	 *  another object is instantiated which keeps the vector as an
	 *  instance variable.
	 */
	@Command( shortDescription = "Vector iteration performance example", helpText = "Iterates over a large area through an efficient and inefficient way" )
	public String testPerformancePlaceholder(@CommandParam( value = "Whether to use the efficient version or not" ) boolean efficient) {
		long time = System.currentTimeMillis();
		if(efficient) {
			for(Vector3i vector = new Vector3i(0, 0, 0); vector.x < 1000; vector.x++) {
				for(vector.y = 0; vector.y < 1000; vector.y++) {
					for(vector.z = 0; vector.z < 1000; vector.z++) {
						Math.abs(vector.x + vector.y + vector.z); // Operation inside the loop is unimportant
					}
				}
			}
		} else {
			for(int x = 0; x < 1000; x++) {
				for(int y=0; y < 1000; y++) {
					for(int z=0; z < 1000; z+) {
						Vector3i vector = new Vector3i(x, y, z);
						Math.abs(vector.x + vector.y + vector.z);
					}
				}
			}
		}
		time = System.currentTimeMillis() - time;
		return "That took "+time+"ms";
	}
}
