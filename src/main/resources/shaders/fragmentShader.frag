#version 460 core
in vec2 TexCoord;
in vec3 FragPos;

out vec4 FragColor;

uniform float alpha;
uniform sampler2D ourTexture;

struct AmbientLight {
    vec3 ambient;
};

struct DirectionalLight {
    vec3 direction;

    vec3 ambient;
    vec3 diffuse;
    vec3 specular;
};

struct PointLight {
    vec3 position;

    vec3 ambient;
    vec3 diffuse;
    vec3 specular;

    float constant;
    float linear;
    float quadratic;
};
struct SpotLight {
    vec3 position;
    vec3 direction;
    vec3 ambient;
    vec3 diffuse;
    vec3 specular;
    float innerCutOff;
    float outerCutOff;
    float constant;
    float linear;
    float quadratic;
};

uniform AmbientLight ambientLight;
uniform DirectionalLight directionalLight;
uniform PointLight pointLight;
uniform SpotLight spotLight;
uniform vec3 viewPos;
uniform float shininess;
uniform vec3 specularColor;

void main() {
    vec3 dX = dFdx(FragPos);
    vec3 dY = dFdy(FragPos);
    vec3 normal = normalize(cross(dX, dY));

    vec4 texColor = texture(ourTexture, TexCoord);
    vec3 baseColor = texColor.rgb;

    vec3 lightDir = normalize(-directionalLight.direction);
    float diff = max(dot(normal, lightDir), 0.0);

    vec3 globalAmbient = ambientLight.ambient * baseColor;
    vec3 ambient = directionalLight.ambient * baseColor;

    vec3 diffuse = directionalLight.diffuse * diff * baseColor;

    vec3 finalColor = ambient + diffuse;

    FragColor = vec4(finalColor, texColor.a * alpha);
}
