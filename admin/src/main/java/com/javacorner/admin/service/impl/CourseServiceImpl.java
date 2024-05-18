package com.javacorner.admin.service.impl;

import com.javacorner.admin.dao.InstructorDao;
import com.javacorner.admin.dto.CourseDTO;
import com.javacorner.admin.entity.Course;
import com.javacorner.admin.entity.Instructor;
import com.javacorner.admin.mapper.CourseMapper;
import com.javacorner.admin.mapper.InstructorMapper;
import com.javacorner.admin.service.CourseService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import com.javacorner.admin.dao.CourseDao;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.stream.Collectors;

public class CourseServiceImpl implements CourseService {

    private CourseDao courseDao;

    private CourseMapper courseMapper;

    private InstructorDao instructorDao;

    public CourseServiceImpl(CourseDao courseDao, CourseMapper courseMapper, InstructorDao instructorDao) {
        this.courseDao = courseDao;
        this.courseMapper = courseMapper;
        this.instructorDao = instructorDao;
    }


    @Override
    public Course loadCourseById(Long courseId) {

        return courseDao.findById(courseId).orElseThrow(() -> new EntityNotFoundException("Course with ID " + courseId + " Not Found"));
    }

    @Override
    public CourseDTO createCourse(CourseDTO courseDTO) {

        Course course = courseMapper.fromCourseDTO(courseDTO);
        Instructor instructor = instructorDao.findById(courseDTO.getInstructor().getInstructorId()).orElseThrow(() -> new EntityNotFoundException("Instructor with ID " + courseDTO.getInstructor().getInstructorId() + " Not Found"));
        course.setInstructor(instructor);
        Course savedCourse = courseDao.save(course);
        return courseMapper.fromCourse(savedCourse);
    }

    @Override
    public CourseDTO updateCourse(CourseDTO courseDTO) {

        Course loadedCourse = loadCourseById(courseDTO.getCourseId());
        Instructor instructor = instructorDao.findById(courseDTO.getInstructor().getInstructorId()).orElseThrow(() -> new EntityNotFoundException("Instructor with ID " + courseDTO.getInstructor().getInstructorId() + " Not Found"));
        Course course = courseMapper.fromCourseDTO(courseDTO);
        course.setInstructor(instructor);
        course.setStudents(loadedCourse.getStudents());
        Course updatedCourse = courseDao.save(course);
        return courseMapper.fromCourse(updatedCourse);
    }

    @Override
    public Page<CourseDTO> findCoursesByCourseName(String keyword, int page, int size) {

        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Course> coursePage = courseDao.findCoursesByCourseNameContains(keyword, pageRequest);
        return new PageImpl<>(coursePage.getContent().stream().map(course -> courseMapper.fromCourse(course)).collect(Collectors.toList()));
    }

    @Override
    public void assignStudentToCourse(Long courseId, Long studentId) {

    }

    @Override
    public Page<CourseDTO> fetchCoursesForStudent(Long studentId, int page, int size) {
        return null;
    }

    @Override
    public Page<CourseDTO> fetchNonEnrolledInCoursesForStudent(Long studentId, int page, int size) {
        return null;
    }

    @Override
    public void removeCourse(Long courseId) {

    }

    @Override
    public Page<CourseDTO> fetchCoursesForInstructor(Long instructorId, int page, int size) {
        return null;
    }
}
