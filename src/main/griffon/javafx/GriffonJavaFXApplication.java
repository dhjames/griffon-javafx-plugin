/*
 * Copyright 2009-2011 the original author or authors.
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

package griffon.javafx;

import javafx.stage.Stage;
import griffon.core.UIThreadManager;
import org.codehaus.griffon.runtime.core.AbstractGriffonApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Dean Iverson
 */
public class GriffonJavaFXApplication extends AbstractGriffonApplication {
    private static final Logger LOG = LoggerFactory.getLogger(GriffonJavaFXApplication.class);
    Stage primaryStage;

    public GriffonJavaFXApplication() {
        this(AbstractGriffonApplication.EMPTY_ARGS);
    }

    public GriffonJavaFXApplication(String[] args) {
        super(args);
        // windowManager = new WindowManager(this);
        UIThreadManager.getInstance().setUIThreadHandler(new JavaFXUIThreadHandler());
        // addShutdownHandler(windowManager);
    }
    
    /**
     * Provide access to the primary stage.
     * @return The primary Stage of this application.
     */
    Stage getPrimaryStage() {
        return primaryStage;
    }
    
    void setPrimaryStage(Stage stage) {
        primaryStage = stage;
    }

    public Object createApplicationContainer() {
        Stage stage = new Stage();
        // TODO: create WindowManager
        // windowManager.attach(stage);
        return stage;
    }
}
