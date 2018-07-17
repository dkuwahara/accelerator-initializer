/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.hopper.initializer.creator.impl;

import com.hopper.initializer.creator.FileCreator;
import com.hopper.initializer.creator.ProjectCreator;
import com.hopper.initializer.creator.annotation.SpringBoot;
import com.hopper.initializer.model.ProjectCreation;
import com.hopper.model.ApplicationType;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

@Component
class SpringBootProjectCreatorImpl implements ProjectCreator<ProjectCreation> {

    private List<FileCreator<ProjectCreation>> fileCreators;

    public SpringBootProjectCreatorImpl(@SpringBoot List<FileCreator<ProjectCreation>> fileCreators) {
        this.fileCreators = checkNotNull(fileCreators);
    }

    @Override
    public boolean skip(ProjectCreation projectCreation) {
        return ApplicationType.JAVA_SPRING_BOOT != projectCreation.getType() && ApplicationType.JAVA_SPRING_BOOT_2 != projectCreation.getType();
    }

    @Override
    public void create(ProjectCreation request) {
        fileCreators
                .stream()
                .sorted(Comparator.comparingInt(FileCreator::order))
                .forEach(creator -> creator.create(request));
    }
}