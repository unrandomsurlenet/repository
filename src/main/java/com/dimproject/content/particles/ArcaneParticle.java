package com.dimproject.content.particles;


import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;

public class ArcaneParticle extends TextureSheetParticle {

    private final SpriteSet sprites;

    protected ArcaneParticle(ClientLevel level, double x, double y, double z,
            double dx, double dy, double dz, SpriteSet sprites) {
    	super(level, x, y, z, dx, dy, dz);
    	this.sprites = sprites;

    	this.lifetime = 25 + this.random.nextInt(10);

    	// Plus petite
    	this.quadSize = 0.08f + this.random.nextFloat() * 0.08f;

    		// Direction sphérique uniforme
    	double theta = this.random.nextDouble() * Math.PI * 2;   // angle horizontal
	double phi = Math.acos(2 * this.random.nextDouble() - 1); // angle vertical
	double speed = 0.15 + this.random.nextDouble() * 0.06;

	this.xd = Math.sin(phi) * Math.cos(theta) * speed;
	this.yd = Math.cos(phi) * speed;
	this.zd = Math.sin(phi) * Math.sin(theta) * speed;

	this.gravity = 0.0f; // pas de gravité pour garder la forme sphérique
	this.alpha = 1.0f;
	this.setSpriteFromAge(sprites);
    }

    @Override
    public void tick() {
        super.tick();
        // Change de sprite selon l'âge (anime les 8 frames sur la durée de vie)
        this.setSpriteFromAge(sprites);
        // Drag
        this.xd *= 0.9;
        this.yd *= 0.9;
        this.zd *= 0.9;
        // Fade out
        this.alpha = 1.0f - (float) this.age / (float) this.lifetime;
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(SpriteSet sprites) {
            this.sprites = sprites;
        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel level,
                                       double x, double y, double z,
                                       double dx, double dy, double dz) {
            return new ArcaneParticle(level, x, y, z, dx, dy, dz, sprites);
        }
    }
}