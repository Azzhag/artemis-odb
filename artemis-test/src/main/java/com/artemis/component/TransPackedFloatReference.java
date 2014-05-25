package com.artemis.component;

import java.nio.ByteBuffer;

import com.artemis.Entity;
import com.artemis.PackedComponent;
import com.artemis.util.Vec2f;

public class TransPackedFloatReference extends PackedComponent {

	private int $stride;
	private static final int $_SIZE_OF = 8;
	private static ByteBuffer $data = ByteBuffer.allocateDirect(64 * $_SIZE_OF);
	

	@Override
	protected PackedComponent forEntity(Entity e) {
		this.$stride = $_SIZE_OF * e.getId();
		if (($data.capacity() - $_SIZE_OF) <= $stride) $grow();
		return this;
	}

	@Override
	protected void reset() {
		$data.putFloat($stride + 0, 0);
		$data.putFloat($stride + 4, 0);
	}
	
	private static void $grow()
	{
		ByteBuffer newBuffer = ByteBuffer.allocateDirect($data.capacity() * 2);
		newBuffer.put($data);
		$data = newBuffer;
	}
	
	public float x() {
		return $data.getFloat($stride + 0);
	}
	
	public float y() {
		return $data.getFloat($stride + 4);
	}
	
	public void x(float value) {
		$data.putFloat($stride + 0, value);
	}
	
	public void y(float value) {
		$data.putFloat($stride + 4, value);
	}
	
	
	public void set(Vec2f v) {
		$data.putFloat($stride + 0, v.x());
		$data.putFloat($stride + 4, v.y);
	}
}
