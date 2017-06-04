package kr.bookstorage.security.service;

import kr.bookstorage.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.social.connect.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by 권성봉 on 2016. 8. 29..
 */
@Slf4j
public class CustomSocialUsersConnectionRepository implements UsersConnectionRepository {
    private CmmSocialAndUserDetailService userService;

    private ConnectionFactoryLocator connectionFactoryLocator;
    private ConnectionSignUp connectionSignUp;

    public CustomSocialUsersConnectionRepository(CmmSocialAndUserDetailService userService, ConnectionFactoryLocator connectionFactoryLocator){
        this.userService = userService;
        this.connectionFactoryLocator = connectionFactoryLocator;
    }

    @Override
    public List<String> findUserIdsWithConnection(Connection<?> connection) {
        try {
            User user = userService.loadUserByConnectionKey(connection.getKey());
            user.getUserSocial().setAccessToken(connection.createData().getAccessToken());
            userService.updateUserSocial(user);
            return Arrays.asList(user.getUniqueId().toString());
        } catch (Exception e) {
            return Arrays.asList(connectionSignUp.execute(connection));
        }
    }

    @Override
    public Set<String> findUserIdsConnectedTo(String providerId, Set<String> providerUserIds) {
        Set<String> keys = new HashSet<>();
        for (String userId : providerUserIds) {
            ConnectionKey ck = new ConnectionKey(providerId, userId);
            try {
                keys.add(userService.loadUserByConnectionKey(ck).getUniqueId().toString());
            } catch (AuthenticationException ae) {
                //ignore
            }
        }
        return keys;
    }

    @Override
    public ConnectionRepository createConnectionRepository(String userId) {
        final ConnectionRepository connectionRepository = new CustomJdbcConnectionRepository(userId, connectionFactoryLocator, userService);
        final User user = userService.findByUserId(userId);
        final ConnectionData connectionData = new ConnectionData(
                user.getUserSocial().getProviderId().name(),
                user.getUserSocial().getProviderUserId(),
                null, null, null,
                user.getUserSocial().getAccessToken(),
                null, null, null
        );

        final Connection connection = connectionFactoryLocator.getConnectionFactory(user.getUserSocial().getProviderId().name()).createConnection(connectionData);

        connectionRepository.addConnection(connection);
        return connectionRepository;
    }

    public void setConnectionSignUp(ConnectionSignUp connectionSignUp) {
        this.connectionSignUp = connectionSignUp;
    }
}
