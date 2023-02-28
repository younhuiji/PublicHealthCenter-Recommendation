package com.example.product.direction.controller;

import com.example.product.direction.dto.InputDto;
import com.example.product.publicHealthCenter.service.PublicHealthCenterRecommendationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequiredArgsConstructor
public class FormController {

    private final PublicHealthCenterRecommendationService  publicHealthCenterRecommendationService;

    @GetMapping("/")
    public String main() {
        return "main";
    }

    @PostMapping("/search")
    public ModelAndView postDirection(@ModelAttribute InputDto inputDto){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("output");
        modelAndView.addObject("outputFormList",
                publicHealthCenterRecommendationService.recommendPublicHealthCenterList(inputDto.getAddress()));
        return modelAndView;
    }



}
