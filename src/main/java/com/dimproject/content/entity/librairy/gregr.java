//package com.dimproject.content.entity.librairy;
//
//import net.minecraft.world.entity.PathfinderMob;
//
//public class ChickenWorkerEntity extends PathfinderMob implements GeoEntity {
//    protected static final RawAnimation FLY_ANIM = RawAnimation.begin().thenLoop("move.fly");
//
//    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);
//
//    public ExampleEntity(EntityType<? extends ExampleEntity> type, Level level) {
//        super(type, level);
//    }
//
//    @Override
//    public void registerControllers(final AnimatableManager.ControllerRegistrar controllers) {
//        controllers.add(new AnimationController<>(this, "Flying", 5, this::flyAnimController));
//    }
//    
//    protected <E extends ExampleEntity> PlayState flyAnimController(final AnimationState<E> event) {
//        if (event.isMoving())
//            return event.setAndContinue(FLY_ANIM);
//
//        return PlayState.STOP;
//    }
//
//    @Override
//    public AnimatableInstanceCache getAnimatableInstanceCache() {
//        return this.geoCache;
//    }
//}