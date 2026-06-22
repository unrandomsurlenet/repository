#version 150

uniform sampler2D DiffuseSampler;
uniform float Intensity;

in vec2 texCoord;
in vec2 oneTexel;
out vec4 fragColor;

vec3 adjustSaturation(vec3 color, float saturation) {
    float gray = dot(color, vec3(0.299, 0.587, 0.114));
    vec3 result = mix(vec3(gray), color, saturation);
    return clamp(result, 0.0, 1.0);
}

void main() {
    float blurRadius = Intensity * 4.0*0.01;
    vec4 color = vec4(0.0);
    float total = 0.0;

    for (int x = -2; x <= 2; x++) {
        for (int y = -2; y <= 2; y++) {
            vec2 offset = vec2(float(x), float(y)) * oneTexel * blurRadius;
            color += texture(DiffuseSampler, texCoord + offset);
            total += 1.0;
        }
    }
    color /= total;

    float saturationBoost = 1.0 + Intensity * 1.5;
    color.rgb = adjustSaturation(color.rgb, saturationBoost*0);

    fragColor = color;
}