package com.oplever.sioe.cucumber.stepdefs;

import com.oplever.sioe.OficialiaElectoralApp;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;

import org.springframework.boot.test.context.SpringBootTest;

@WebAppConfiguration
@SpringBootTest
@ContextConfiguration(classes = OficialiaElectoralApp.class)
public abstract class StepDefs {

    protected ResultActions actions;

}
