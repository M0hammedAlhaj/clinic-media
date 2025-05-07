package com.spring.clinicmedia.infrastructure.factory.user;

import com.spring.clinicmedia.domain.command.UserCreationCommand;
import com.spring.clinicmedia.domain.factory.SpecificUserCreator;
import com.spring.clinicmedia.domain.factory.UserFactory;
import com.spring.clinicmedia.domain.model.UserType;
import com.spring.clinicmedia.domain.model.enitity.user.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UserFactoryAdapter implements UserFactory<UserCreationCommand> {

    private Map<UserType, SpecificUserCreator> creators;

    private final List<SpecificUserCreator> creatorList;

    public UserFactoryAdapter(List<SpecificUserCreator> creatorsList) {
        this.creatorList = creatorsList;
        this.creators = creatorList.stream()
                .collect(Collectors.toMap(SpecificUserCreator::getUserType, creator -> creator));
    }

    @Override
    public User create(UserCreationCommand command) {
        SpecificUserCreator creator = creators.get(command.getUserType());
        if (creator == null) {
            throw new IllegalArgumentException("Unsupported user type: " + command.getUserType());
        }
        return creator.create(command);
    }

}
