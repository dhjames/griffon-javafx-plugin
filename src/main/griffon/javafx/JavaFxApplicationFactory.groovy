/*
 * Copyright 2008-2011 the original author or authors.
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

package griffon.javafx

import javafx.stage.Stage
import javafx.scene.Scene
import javafx.scene.Group
import groovyx.javafx.factory.SceneWrapper

/**
 * @author Dean Iverson
 */
class JavaFxApplicationFactory extends AbstractFactory {

    Object newInstance(FactoryBuilderSupport builder, Object name, Object value, Map attributes) {
        return builder.app.stage;
    }

    @Override
    boolean onHandleNodeAttributes(FactoryBuilderSupport builder, Object node, Map attributes) {
        def stage = node as Stage
        attributes.each { key, value ->
            if (key == "title")
                stage.title = value

        }

        if (!stage.title && builder.app.config?.application?.title)
            stage.title = builder.app.config.application.title

        return true
    }

    @Override
    void onNodeCompleted(FactoryBuilderSupport builder, Object parent, Object node) {
        def stage = node as Stage
        if (builder.variables.containsKey("sceneWrapper"))
            stage.scene = builder.sceneWrapper.createScene()
        stage.visible = true
    }

    @Override
    void setChild(FactoryBuilderSupport builder, Object parent, Object child) {
        if (child instanceof SceneWrapper) {
            builder.sceneWrapper = child as SceneWrapper
        }
    }
}
