package az.atl.project.eTaskify.service.serviceImpl;

import az.atl.project.eTaskify.service.SecurityService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {

    @Override
    public String findByUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
