package io.github.d1bg.gs.model;

import io.github.d1bg.gs.shaders.ShaderProgram;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private final List<ModelElement> elements = new ArrayList<>();

    public void addElement(ModelElement element) {
        elements.add(element);
    }

    public List<ModelElement> getElements() {
        return elements;
    }

    public void render(ShaderProgram shader) {
        for (ModelElement element : elements) {
            element.render(shader);
        }
    }
}
