package bg.tu_varna.sit.gs.demo.objects;

import bg.tu_varna.sit.gs.demo.assets.RectangleGeometry;
import bg.tu_varna.sit.gs.demo.assets.TriangleGeometry;
import bg.tu_varna.sit.gs.mesh.DrawMode;
import bg.tu_varna.sit.gs.mesh.Mesh;
import bg.tu_varna.sit.gs.model.ModelElement;
import bg.tu_varna.sit.gs.scene.SceneObject;

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