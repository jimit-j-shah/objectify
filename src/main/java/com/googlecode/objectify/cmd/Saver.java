package com.googlecode.objectify.cmd;

import com.google.appengine.api.datastore.Entity;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Result;

import java.util.Map;


/**
 * <p>The top element in the command chain for saving entities in the datastore.</p>
 *
 * @author Jeff Schnitzer <jeff@infohazard.org>
 */
public interface Saver
{
	/**
	 * <p>Asynchronously save a single entity in the datastore.<p>
	 *
	 * <p>If the entity has a null Long id, the value will be autogenerated and populated on the entity object
	 * when the async operation completes.  If you require this value, call now() on the result.</p>
	 *
	 * <p>Puts do not cascade.</p>
	 *
	 * @param entity must be a registered entity type
	 * @return an asynchronous result.  To force a synchronous save, call Result.now().
	 */
	<E> Result<Key<E>> entity(E entity);

	/**
	 * <p>Asynchronously save a batch of entities in the datastore.</p>
	 *
	 * <p>If any entities have null Long ids, the values will be autogenerated and populated on the entity objects
	 * when the async operation completes.  If you require these values, call now() on the result.</p>
	 *
	 * <p>Puts do not cascade.</p>
	 *
	 * @param entities must be registered entity types
	 * @return an asynchronous result.  To force a synchronous save, call Result.now().
	 */
	<E> Result<Map<Key<E>, E>> entities(Iterable<E> entities);

	/**
	 * A convenience method for entities(Iterable)
	 */
	<E> Result<Map<Key<E>, E>> entities(E... entities);

	/**
	 * Convert a POJO object to a native datastore Entity.  This is like a save() operation but without actually saving
	 * the data to the datastore.
	 *
	 * @param pojo must be an instance of a registered pojo entity type.
	 * @return the native datastore Entity equivalent of the pojo; exactly what Objectify would save if you saved the POJO normally.
	 */
	Entity toEntity(Object pojo);
}
