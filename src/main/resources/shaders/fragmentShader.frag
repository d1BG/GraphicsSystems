#version 460 core
in vec2 TexCoord;
out vec4 FragColor;
uniform float alpha;

uniform sampler2D ourTexture;

void main() {
    FragColor = texture(ourTexture, TexCoord);
}
