#version 150

uniform sampler2D DiffuseSampler;

in vec2 texCoord;
out vec4 fragColor;

vec3 adjustSaturation(vec3 color, float saturation) {
    float gray = dot(color, vec3(0.299, 0.587, 0.114));
    return clamp(mix(vec3(gray), color, saturation), 0.0, 1.0);
}


void main() {
    vec4 color = texture(DiffuseSampler, texCoord);

    float saturationBoost = 2.5;
    color.rgb = adjustSaturation(color.rgb, saturationBoost);

    fragColor = color;
}