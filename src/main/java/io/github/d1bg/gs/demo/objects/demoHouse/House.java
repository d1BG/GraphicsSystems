package io.github.d1bg.gs.demo.objects.demoHouse;

import io.github.d1bg.gs.demo.assets.demoHouse.RectangleGeometry;
import io.github.d1bg.gs.demo.assets.demoHouse.TriangleGeometry;
import io.github.d1bg.gs.mesh.DrawMode;
import io.github.d1bg.gs.mesh.Mesh;
import io.github.d1bg.gs.model.ModelElement;
import io.github.d1bg.gs.scene.SceneObject;

public class House extends SceneObject {

        public House() {
            buildHouse();
        }

        private void buildHouse() {
            Mesh baseMesh = new Mesh(DrawMode.TRIANGLES, new RectangleGeometry());
            ModelElement rectangle = new ModelElement(baseMesh);
            getModel().addElement(rectangle);

            Mesh roofMesh = new Mesh(DrawMode.TRIANGLES, new TriangleGeometry());
            ModelElement triangle = new ModelElement(roofMesh);
            getModel().addElement(triangle);
        }

        @Override
        public void update() {
        }
    }