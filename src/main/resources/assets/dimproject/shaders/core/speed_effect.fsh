#version 150

in vec2 texCoord;
out vec4 fragColor;

uniform float Time;
uniform float Intensity;
uniform vec2 Resolution;

float hash(float n) {
    return fract(sin(n) * 43758.5453);
}

void main() {
    vec2 uv = texCoord - 0.5;
    uv.x *= Resolution.x / Resolution.y;

    float angle = atan(uv.y, uv.x);
    float dist = length(uv);

    if (dist < 0.15) discard;

    float lineCount = 120.0;
    float sector = angle / (3.14159265 * 2.0);
    float sectorId = floor(sector * lineCount);

    // Largeur variable par trait
    float lineWidth = hash(sectorId) * 0.12 + 0.002;
    float lineOffset = (hash(sectorId + 50.0) - 0.5) * 0.015;
    float localAngle = fract(sector * lineCount) - 0.5 + lineOffset;
    float line = smoothstep(lineWidth, lineWidth * 0.3, abs(localAngle));

    // Défilement vers l'extérieur
    float speed = hash(sectorId + 200.0) * 0.005 + 0.01;
    float scroll = fract(dist - Time * speed * (1.0 + Intensity * 4.0));
    // Trait long et fin qui défile — pas de pulse, juste un front continu
    float streak = smoothstep(0.0, 0.02, scroll) * smoothstep(0.15, 0.08, scroll);

    // Fondu centre/bords
    float centerFade = smoothstep(0.05, 0.2, dist);
    float edgeFade = smoothstep(0.75, 0.55, dist);

    float brightness = line * streak * centerFade * edgeFade;

    if (brightness * Intensity < 0.02) discard;


    // Noir pur style manga
    fragColor = vec4(0.0, 0.0, 0.0, brightness * Intensity);
}