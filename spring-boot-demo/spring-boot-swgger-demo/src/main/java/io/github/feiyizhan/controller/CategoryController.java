package io.github.feiyizhan.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 徐明龙 XuMingLong 2020-07-21
 */
@RestController
public class CategoryController {
    @RequestMapping(value = "/category/Resource/", method = RequestMethod.GET)
    public ResponseEntity<String> search(@ModelAttribute(value = "someEnum") Category someEnum) {
        return ResponseEntity.ok(someEnum.name());
    }

    @RequestMapping(value = "/category/map", method = RequestMethod.GET)
    public Map<String, Map<String, Pet>> map() {
        return new HashMap<>();
    }

    @RequestMapping(value = "/category/{id}", method = RequestMethod.POST)
    public ResponseEntity<Void> someOperation(
        @PathVariable long id,
        @RequestBody int userId) {
        return ResponseEntity.ok(null);
    }

    @RequestMapping(value = "/category/{id}/{userId}", method = RequestMethod.POST)
    public ResponseEntity<Void> ignoredParam(
        @PathVariable long id,
        @PathVariable @ApiIgnore int userId) {
        return ResponseEntity.ok(null);
    }

    @RequestMapping(value = "/category/{id}/map", method = RequestMethod.POST)
    public ResponseEntity<Void> map(
        @PathVariable String id,
        @RequestParam Map<String, String> test) {
        return ResponseEntity.ok(null);
    }

    @RequestMapping(value = "/categories/", method = RequestMethod.POST)
    public ResponseEntity<List<Category>> map(String[] categories) {
        return ResponseEntity.ok(null);
    }

}