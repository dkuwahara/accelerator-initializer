/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.hopper.initializer.creator.react;

import com.hopper.initializer.FileProcessor;
import com.hopper.initializer.creator.FileCreator;
import com.hopper.initializer.creator.annotation.React;
import com.hopper.initializer.model.ProjectCreation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Paths;

import static com.google.common.base.Preconditions.checkNotNull;

@Component
@Slf4j
@React
class WebpackConfigJsCreator implements FileCreator<ProjectCreation> {
    static final String WEBPACK_CONFIG_JS_TEMPLATE_PATH = "templates/projectCreation/react/webpack.config.js.tpl";
    private final FileProcessor fileProcessor;

    public WebpackConfigJsCreator(FileProcessor fileProcessor) {
        this.fileProcessor = checkNotNull(fileProcessor);
    }
    
    @Override
    public void create(ProjectCreation request) {
        log.info("Creating webpack.config.js file");
        InputStream inputStream = this.fileProcessor.loadResourceFromClassPath(WEBPACK_CONFIG_JS_TEMPLATE_PATH);
        File file = this.fileProcessor.touch(Paths.get(request.getRootDir(), "webpack.config.js"));
        this.fileProcessor.copy(inputStream, file);
    }
}