/*
 * Copyright 2014 the original author or authors.
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
package org.gradle.api.internal.artifacts.metadata;

import org.apache.ivy.core.module.descriptor.ModuleDescriptor;
import org.gradle.api.artifacts.ModuleVersionIdentifier;
import org.gradle.api.internal.artifacts.ivyservice.NamespaceId;
import org.gradle.api.artifacts.component.ModuleComponentIdentifier;
import org.gradle.api.internal.artifacts.ivyservice.IvyUtil;

import java.util.Map;

public class DefaultIvyModuleVersionMetaData extends AbstractModuleVersionMetaData implements IvyModuleVersionMetaData {
    private final Map<NamespaceId, String> extraInfo;

    public DefaultIvyModuleVersionMetaData(ModuleDescriptor moduleDescriptor) {
        super(moduleDescriptor);
        this.extraInfo = moduleDescriptor.getExtraInfo();
    }

    public DefaultIvyModuleVersionMetaData(ModuleVersionIdentifier identifier, ModuleDescriptor moduleDescriptor, ModuleComponentIdentifier componentIdentifier) {
        super(identifier, moduleDescriptor, componentIdentifier);
        this.extraInfo = moduleDescriptor.getExtraInfo();
    }

    public DefaultIvyModuleVersionMetaData(DependencyMetaData dependencyMetaData) {
        this(IvyUtil.createModuleDescriptor(dependencyMetaData.getDescriptor()));
    }

    @Override
    public DefaultIvyModuleVersionMetaData copy() {
        // TODO:ADAM - need to make a copy of the descriptor (it's effectively immutable at this point so it's not a problem yet)
        DefaultIvyModuleVersionMetaData copy = new DefaultIvyModuleVersionMetaData(getId(), getDescriptor(), getComponentId());
        copyTo(copy);
        return copy;
    }

    public String getBranch() {
        return getDescriptor().getModuleRevisionId().getBranch();
    }

    public Map<NamespaceId, String> getExtraInfo() {
        return extraInfo;
    }
}
