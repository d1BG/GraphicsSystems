#version 460 core

layout (location = 0) in vec3 aPos;
layout (location = 1) in float aBlockId;

out vec3 LocalPos;
out vec3 FragPos;
out float BlockId;

uniform mat4 modelMatrix;
uniform mat4 viewMatrix;
uniform mat4 projectionMatrix;

void main() {
    vec4 worldPos = modelMatrix * vec4(aPos, 1.0);
    FragPos = aPos;
    LocalPos = aPos;

    gl_Position = projectionMatrix * viewMatrix * worldPos;
    BlockId = aBlockId;
}
