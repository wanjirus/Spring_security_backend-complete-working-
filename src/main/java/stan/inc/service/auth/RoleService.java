package stan.inc.service.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stan.inc.exceptions.ResourceNotFoundException;
import stan.inc.models.user.Role;
import stan.inc.repository.RoleRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    // Find Role By id
    @Transactional
    public Role findRoleById(Long id) throws ResourceNotFoundException {
        return roleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No role record found with id::"+id));
    }

    // Get All Roles Service
    @Transactional
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    // Get Role By name.
//    @Transactional
//    public Role getRoleByName(String roleName) throws ResourceNotFoundException{
//        return roleRepository.findByName(RoleName).orElseThrow(()-> new ResourceNotFoundException("No role record found with role name ::" + roleName));
//    }
}
