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
class StaticfileCreator implements FileCreator<ProjectCreation> {
    static final String STATICFILE_TEMPLATE_PATH = "templates/projectCreation/react/Staticfile.tpl";
    private final FileProcessor fileProcessor;

    public StaticfileCreator(FileProcessor fileProcessor) {
        this.fileProcessor = checkNotNull(fileProcessor);
    }
    
    @Override
    public void create(ProjectCreation request) {
        log.info("Creating Staticfile");
        InputStream inputStream = this.fileProcessor.loadResourceFromClassPath(STATICFILE_TEMPLATE_PATH);
        File file = this.fileProcessor.touch(Paths.get(request.getRootDir(), "Staticfile"));
        this.fileProcessor.copy(inputStream, file);
    }
}