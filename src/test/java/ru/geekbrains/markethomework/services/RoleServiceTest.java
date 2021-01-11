package ru.geekbrains.markethomework.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.geekbrains.markethomework.entities.Role;
import ru.geekbrains.markethomework.repositories.RoleRepository;

import java.util.Optional;

@SpringBootTest(classes = RoleService.class)
public class RoleServiceTest {
    @Autowired
    RoleService roleService;

    @MockBean
    RoleRepository roleRepository;

    @Test
    public void findByIdTest() {
        Role roleInDB = new Role();
        roleInDB.setName("USER");
        roleInDB.setId(1L);

        Mockito.doReturn(Optional.of(roleInDB))
                .when(roleRepository)
                .findById(1L);

        Role role = roleService.findById(1L);
        Assertions.assertNotNull(role);
        Assertions.assertEquals(roleInDB.getName(), role.getName());
        Mockito.verify(roleRepository, Mockito.times(1)).findById(ArgumentMatchers.eq(1L));
    }

    @Test
    public void findByNameTest() {
        Role roleInDB = new Role();
        roleInDB.setName("ADMIN");
        roleInDB.setId(1L);

        Mockito.doReturn(Optional.of(roleInDB))
                .when(roleRepository)
                .findByName("ADMIN");

        Role role = roleService.findByName("ADMIN");
        Assertions.assertNotNull(role);
        Assertions.assertEquals(roleInDB.getName(), role.getName());
        Mockito.verify(roleRepository, Mockito.times(1)).findByName(ArgumentMatchers.eq("ADMIN"));
    }

    @Test
    public void findUserRoleTest() {
        Role roleInDB = new Role();
        roleInDB.setName("USER");
        roleInDB.setId(1L);

        Mockito.doReturn(Optional.of(roleInDB))
                .when(roleRepository)
                .findByName("USER");

        Role role = roleService.findByName("USER");
        Assertions.assertNotNull(role);
        Assertions.assertEquals(roleInDB.getName(), role.getName());
        Mockito.verify(roleRepository, Mockito.times(1)).findByName(ArgumentMatchers.eq("USER"));
    }
}
