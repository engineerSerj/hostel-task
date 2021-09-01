package org.hostel.controller;

import lombok.RequiredArgsConstructor;
import org.hostel.domain.CategoryName;
import org.hostel.dto.CategoryDto;
import org.hostel.jms.CategoryRemoveByIdQueueConsumer;
import org.hostel.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.jms.Queue;
import java.util.List;

@RequestMapping("/api/category")
@RequiredArgsConstructor
@RestController
public class CategoryController {

    private final CategoryService categoryService;
    private final Queue queue;
    private final CategoryRemoveByIdQueueConsumer consumer;

    @Autowired
    @Qualifier("queueJmsTemplate")
    private JmsTemplate queueJmsTemplate;

    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> add(@RequestBody CategoryDto categoryDto) {
        return categoryService.add(categoryDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> remove(@PathVariable("id") String id)  {
        queueJmsTemplate.convertAndSend(queue, id);
        return consumer.getResponseEntity();
    }

    @GetMapping("/categoryList")
    @PreAuthorize("hasRole('ADMIN') or hasRole('RECEPTIONIST')")
    public ResponseEntity<List<CategoryName>> getCategoryList() {
        return categoryService.getCategoryList();
    }
}
