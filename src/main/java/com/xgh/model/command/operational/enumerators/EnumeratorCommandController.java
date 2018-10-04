package com.xgh.model.command.operational.enumerators;

import com.xgh.infra.controller.BasicCommandController;
import com.xgh.model.command.operational.enumerators.commands.DeleteEnumerator;
import com.xgh.model.command.operational.enumerators.commands.RegisterEnumerator;
import com.xgh.model.command.operational.enumerators.commands.UpdateEnumerator;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enumerators")
public class EnumeratorCommandController extends BasicCommandController<RegisterEnumerator, UpdateEnumerator, DeleteEnumerator> {
    @Override
    protected String getBasePath() {
        return "/enumerators";
    }
}
