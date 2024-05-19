package com.javacorner.admin.service.impl;

import com.javacorner.admin.dao.InstructorDao;
import com.javacorner.admin.dto.InstructorDTO;
import com.javacorner.admin.entity.Instructor;
import com.javacorner.admin.entity.User;
import com.javacorner.admin.mapper.InstructorMapper;
import com.javacorner.admin.service.InstructorService;
import com.javacorner.admin.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class InstructorServiceImpl implements InstructorService {

    private InstructorDao instructorDao;

    private InstructorMapper instructorMapper;

    private UserService userService;

    public InstructorServiceImpl(InstructorDao instructorDao, InstructorMapper instructorMapper, UserService userService) {
        this.instructorDao = instructorDao;
        this.instructorMapper = instructorMapper;
        this.userService = userService;
    }

    @Override
    public Instructor loadInstructorById(Long instructorId) {
        return instructorDao.findById(instructorId).orElseThrow(() -> new EntityNotFoundException("Instructor with ID " + instructorId + " not found"));
    }

    @Override
    public Page<InstructorDTO> findInstructorsByName(String name, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Instructor> instructorsPage = instructorDao.findInstructorsByName(name, pageRequest);
        return new PageImpl<>(instructorsPage.getContent().stream().map(instructor -> instructorMapper.fromInstructor(instructor)).collect(Collectors.toList()), pageRequest, instructorsPage.getTotalElements());
    }

    @Override
    public InstructorDTO loadInstructorByEmail(String email) {
        return instructorMapper.fromInstructor(instructorDao.findInstructorByEmail(email));
    }

    @Override
    public InstructorDTO createInstructor(InstructorDTO instructorDTO) {
        User user = userService.createUser(instructorDTO.getUser().getEmail(), instructorDTO.getUser().getPassword());
        userService.assignRoleToUser(user.getEmail(), "Instructor");
        Instructor instructor = instructorMapper.fromInstructorDTO(instructorDTO);
        instructor.setUser(user);
        Instructor savedInstructor = instructorDao.save(instructor);
        return instructorMapper.fromInstructor(savedInstructor);
    }

    @Override
    public InstructorDTO updateInstructor(InstructorDTO instructorDTO) {
        return null;
    }

    @Override
    public List<InstructorDTO> fetchInstructors() {
        return null;
    }

    @Override
    public void removeInstructor(Long instructor) {

    }
}
