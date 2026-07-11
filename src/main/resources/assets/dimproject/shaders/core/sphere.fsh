#version 150

in vec2 texCoord;
in vec3 localPos;

out vec4 fragColor;

uniform sampler2D Sampler0; // fond fixe
uniform sampler2D Sampler1; // texture qui se déplace

uniform vec4 SphereColor;
uniform float Progress;
uniform float Time;

void main() {
    float normalizedY = (localPos.y + 30.0) / 60.0;
    if (normalizedY > Progress) discard;
    float edge = smoothstep(Progress - 0.05, Progress, normalizedY);

    // Layer 0 : fond fixe avec projection triplanaire (pas de déformation, pas de mouvement)
    vec3 blendWeights = abs(normalize(localPos));
    blendWeights = pow(blendWeights, vec3(4.0));
    blendWeights /= (blendWeights.x + blendWeights.y + blendWeights.z);

    float baseScale = 0.05;
    vec4 xBase = texture(Sampler0, localPos.yz * baseScale);
    vec4 yBase = texture(Sampler0, localPos.xz * baseScale);
    vec4 zBase = texture(Sampler0, localPos.xy * baseScale);
    vec4 base = xBase * blendWeights.x + yBase * blendWeights.y + zBase * blendWeights.z;

    // Layer 1 : texture qui se déplace via animOffset
    float moveScale = 0.05;
    vec2 animOffset = vec2(Time * 0.05, Time * 0.03);
    vec4 xMove = texture(Sampler1, localPos.yz * moveScale + animOffset);
    vec4 yMove = texture(Sampler1, localPos.xz * moveScale + animOffset);
    vec4 zMove = texture(Sampler1, localPos.xy * moveScale + animOffset);
    vec4 moving = xMove * blendWeights.x + yMove * blendWeights.y + zMove * blendWeights.z;

    // Blend
    vec3 color = mix(base.rgb, moving.rgb, moving.a * 0.6);
    fragColor = vec4(color * SphereColor.rgb, SphereColor.a * (1.0 - edge));
}