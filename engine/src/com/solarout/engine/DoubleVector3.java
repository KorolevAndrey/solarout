package com.solarout.engine;

/*******************************************************************************
 * Copyright 2011 See AUTHORS file.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

import com.badlogic.gdx.math.*;
import com.badlogic.gdx.utils.NumberUtils;

import java.io.Serializable;

/** Encapsulates a 3D vector. Allows chaining operations by returning a reference to itself in all modification methods.
 * @author badlogicgames@gmail.com */
public class DoubleVector3 implements Serializable {
    private static final long serialVersionUID = 3840054589596672522L;

    /** the x-component of this vector **/
    public double x;
    /** the y-component of this vector **/
    public double y;
    /** the z-component of this vector **/
    public double z;

    public final static DoubleVector3 X = new DoubleVector3(1, 0, 0);
    public final static DoubleVector3 Y = new DoubleVector3(0, 1, 0);
    public final static DoubleVector3 Z = new DoubleVector3(0, 0, 1);
    public final static DoubleVector3 Zero = new DoubleVector3(0, 0, 0);

    private final static Matrix4 tmpMat = new Matrix4();

    /** Constructs a vector at (0,0,0) */
    public DoubleVector3() {
    }

    /** Creates a vector with the given components
     * @param x The x-component
     * @param y The y-component
     * @param z The z-component */
    public DoubleVector3(double x, double y, double z) {
        this.set(x, y, z);
    }

    /** Creates a vector from the given vector
     * @param vector The vector */
    public DoubleVector3(final DoubleVector3 vector) {
        this.set(vector);
    }

    /** Creates a vector from the given array. The array must have at least 3 elements.
     *
     * @param values The array */
    public DoubleVector3(final double[] values) {
        this.set(values[0], values[1], values[2]);
    }

    /** Creates a vector from the given vector and z-component
     *
     * @param vector The vector
     * @param z The z-component */
    public DoubleVector3(final Vector2 vector, double z) {
        this.set(vector.x, vector.y, z);
    }

    /** Sets the vector to the given components
     *
     * @param x The x-component
     * @param y The y-component
     * @param z The z-component
     * @return this vector for chaining */
    public DoubleVector3 set (double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        return this;
    }

    public DoubleVector3 set (final DoubleVector3 vector) {
        return this.set(vector.x, vector.y, vector.z);
    }

    /** Sets the components from the array. The array must have at least 3 elements
     *
     * @param values The array
     * @return this vector for chaining */
    public DoubleVector3 set (final double[] values) {
        return this.set(values[0], values[1], values[2]);
    }

    /** Sets the components of the given vector and z-component
     *
     * @param vector The vector
     * @param z The z-component
     * @return This vector for chaining */
    public DoubleVector3 set (final Vector2 vector, double z) {
        return this.set(vector.x, vector.y, z);
    }

    
    public DoubleVector3 cpy () {
        return new DoubleVector3(this);
    }

    
    public DoubleVector3 add (final DoubleVector3 vector) {
        return this.add(vector.x, vector.y, vector.z);
    }

    /** Adds the given vector to this component
     * @param x The x-component of the other vector
     * @param y The y-component of the other vector
     * @param z The z-component of the other vector
     * @return This vector for chaining. */
    public DoubleVector3 add (double x, double y, double z) {
        return this.set(this.x + x, this.y + y, this.z + z);
    }

    /** Adds the given value to all three components of the vector.
     *
     * @param values The value
     * @return This vector for chaining */
    public DoubleVector3 add (double values) {
        return this.set(this.x + values, this.y + values, this.z + values);
    }

    
    public DoubleVector3 sub (final DoubleVector3 a_vec) {
        return this.sub(a_vec.x, a_vec.y, a_vec.z);
    }

    /** Subtracts the other vector from this vector.
     *
     * @param x The x-component of the other vector
     * @param y The y-component of the other vector
     * @param z The z-component of the other vector
     * @return This vector for chaining */
    public DoubleVector3 sub (double x, double y, double z) {
        return this.set(this.x - x, this.y - y, this.z - z);
    }

    /** Subtracts the given value from all components of this vector
     *
     * @param value The value
     * @return This vector for chaining */
    public DoubleVector3 sub (double value) {
        return this.set(this.x - value, this.y - value, this.z - value);
    }

    
    public DoubleVector3 scl (double scalar) {
        return this.set(this.x * scalar, this.y * scalar, this.z * scalar);
    }

    
    public DoubleVector3 scl (final DoubleVector3 other) {
        return this.set(x * other.x, y * other.y, z * other.z);
    }

    /** Scales this vector by the given values
     * @param vx X value
     * @param vy Y value
     * @param vz Z value
     * @return This vector for chaining */
    public DoubleVector3 scl (double vx, double vy, double vz) {
        return this.set(this.x * vx, this.y * vy, this.z * vz);
    }

    
    public DoubleVector3 mulAdd (DoubleVector3 vec, double scalar) {
        this.x += vec.x * scalar;
        this.y += vec.y * scalar;
        this.z += vec.z * scalar;
        return this;
    }

    
    public DoubleVector3 mulAdd (DoubleVector3 vec, DoubleVector3 mulVec) {
        this.x += vec.x * mulVec.x;
        this.y += vec.y * mulVec.y;
        this.z += vec.z * mulVec.z;
        return this;
    }

    /** @return The euclidian length */
    public static double len (final double x, final double y, final double z) {
        return (double)Math.sqrt(x * x + y * y + z * z);
    }

    
    public double len () {
        return (double)Math.sqrt(x * x + y * y + z * z);
    }

    /** @return The squared euclidian length */
    public static double len2 (final double x, final double y, final double z) {
        return x * x + y * y + z * z;
    }

    
    public double len2 () {
        return x * x + y * y + z * z;
    }

    /** @param vector The other vector
     * @return Wether this and the other vector are equal */
    public boolean idt (final DoubleVector3 vector) {
        return x == vector.x && y == vector.y && z == vector.z;
    }

    /** @return The euclidian distance between the two specified vectors */
    public static double dst (final double x1, final double y1, final double z1, final double x2, final double y2, final double z2) {
        final double a = x2 - x1;
        final double b = y2 - y1;
        final double c = z2 - z1;
        return (double)Math.sqrt(a * a + b * b + c * c);
    }

    
    public double dst (final DoubleVector3 vector) {
        final double a = vector.x - x;
        final double b = vector.y - y;
        final double c = vector.z - z;
        return (double)Math.sqrt(a * a + b * b + c * c);
    }

    /** @return the distance between this point and the given point */
    public double dst (double x, double y, double z) {
        final double a = x - this.x;
        final double b = y - this.y;
        final double c = z - this.z;
        return (double)Math.sqrt(a * a + b * b + c * c);
    }

    /** @return the squared distance between the given points */
    public static double dst2 (final double x1, final double y1, final double z1, final double x2, final double y2, final double z2) {
        final double a = x2 - x1;
        final double b = y2 - y1;
        final double c = z2 - z1;
        return a * a + b * b + c * c;
    }

    
    public double dst2 (DoubleVector3 point) {
        final double a = point.x - x;
        final double b = point.y - y;
        final double c = point.z - z;
        return a * a + b * b + c * c;
    }

    /** Returns the squared distance between this point and the given point
     * @param x The x-component of the other point
     * @param y The y-component of the other point
     * @param z The z-component of the other point
     * @return The squared distance */
    public double dst2 (double x, double y, double z) {
        final double a = x - this.x;
        final double b = y - this.y;
        final double c = z - this.z;
        return a * a + b * b + c * c;
    }

    
    public DoubleVector3 nor () {
        final double len2 = this.len2();
        if (len2 == 0f || len2 == 1f) return this;
        return this.scl(1f / (double)Math.sqrt(len2));
    }

    /** @return The dot product between the two vectors */
    public static double dot (double x1, double y1, double z1, double x2, double y2, double z2) {
        return x1 * x2 + y1 * y2 + z1 * z2;
    }

    
    public double dot (final DoubleVector3 vector) {
        return x * vector.x + y * vector.y + z * vector.z;
    }

    /** Returns the dot product between this and the given vector.
     * @param x The x-component of the other vector
     * @param y The y-component of the other vector
     * @param z The z-component of the other vector
     * @return The dot product */
    public double dot (double x, double y, double z) {
        return this.x * x + this.y * y + this.z * z;
    }

    /** Sets this vector to the cross product between it and the other vector.
     * @param vector The other vector
     * @return This vector for chaining */
    public DoubleVector3 crs (final DoubleVector3 vector) {
        return this.set(y * vector.z - z * vector.y, z * vector.x - x * vector.z, x * vector.y - y * vector.x);
    }

    /** Sets this vector to the cross product between it and the other vector.
     * @param x The x-component of the other vector
     * @param y The y-component of the other vector
     * @param z The z-component of the other vector
     * @return This vector for chaining */
    public DoubleVector3 crs (double x, double y, double z) {
        return this.set(this.y * z - this.z * y, this.z * x - this.x * z, this.x * y - this.y * x);
    }

    /** Left-multiplies the vector by the given 4x3 column major matrix. The matrix should be composed by a 3x3 matrix representing
     * rotation and scale plus a 1x3 matrix representing the translation.
     * @param matrix The matrix
     * @return This vector for chaining */
    public DoubleVector3 mul4x3 (double[] matrix) {
        return set(x * matrix[0] + y * matrix[3] + z * matrix[6] + matrix[9], x * matrix[1] + y * matrix[4] + z * matrix[7]
                + matrix[10], x * matrix[2] + y * matrix[5] + z * matrix[8] + matrix[11]);
    }



    public boolean isUnit () {
        return isUnit(0.000000001f);
    }

    
    public boolean isUnit (final double margin) {
        return Math.abs(len2() - 1f) < margin;
    }

    
    public boolean isZero () {
        return x == 0 && y == 0 && z == 0;
    }

    
    public boolean isZero (final double margin) {
        return len2() < margin;
    }

    
    public boolean isOnLine (DoubleVector3 other, double epsilon) {
        return len2(y * other.z - z * other.y, z * other.x - x * other.z, x * other.y - y * other.x) <= epsilon;
    }

    
    public boolean isOnLine (DoubleVector3 other) {
        return len2(y * other.z - z * other.y, z * other.x - x * other.z, x * other.y - y * other.x) <= MathUtils.FLOAT_ROUNDING_ERROR;
    }

    
    public boolean isCollinear (DoubleVector3 other, double epsilon) {
        return isOnLine(other, epsilon) && hasSameDirection(other);
    }

    
    public boolean isCollinear (DoubleVector3 other) {
        return isOnLine(other) && hasSameDirection(other);
    }

    
    public boolean isCollinearOpposite (DoubleVector3 other, double epsilon) {
        return isOnLine(other, epsilon) && hasOppositeDirection(other);
    }

    
    public boolean isCollinearOpposite (DoubleVector3 other) {
        return isOnLine(other) && hasOppositeDirection(other);
    }

    
    public boolean isPerpendicular (DoubleVector3 vector) {
        return MathUtils.isZero((float)dot(vector));
    }

    
    public boolean isPerpendicular (DoubleVector3 vector, double epsilon) {
        return MathUtils.isZero((float) dot(vector), (float)epsilon);
    }

    
    public boolean hasSameDirection (DoubleVector3 vector) {
        return dot(vector) > 0;
    }

    
    public boolean hasOppositeDirection (DoubleVector3 vector) {
        return dot(vector) < 0;
    }

    
    public DoubleVector3 lerp (final DoubleVector3 target, double alpha) {
        scl(1.0f - alpha);
        add(target.x * alpha, target.y * alpha, target.z * alpha);
        return this;
    }
    
    public DoubleVector3 interpolate (DoubleVector3 target, double alpha, Interpolation interpolator) {
        return lerp(target, interpolator.apply(0f, 1f, (float) alpha));
    }

    /** Spherically interpolates between this vector and the target vector by alpha which is in the range [0,1]. The result is
     * stored in this vector.
     *
     * @param target The target vector
     * @param alpha The interpolation coefficient
     * @return This vector for chaining. */
    public DoubleVector3 slerp (final DoubleVector3 target, double alpha) {
        final double dot = dot(target);
        // If the inputs are too close for comfort, simply linearly interpolate.
        if (dot > 0.9995 || dot < -0.9995) return lerp(target, alpha);

        // theta0 = angle between input vectors
        final double theta0 = (double)Math.acos(dot);
        // theta = angle between this vector and result
        final double theta = theta0 * alpha;

        final double st = (double)Math.sin(theta);
        final double tx = target.x - x * dot;
        final double ty = target.y - y * dot;
        final double tz = target.z - z * dot;
        final double l2 = tx * tx + ty * ty + tz * tz;
        final double dl = st * ((l2 < 0.0001f) ? 1f : 1f / (double)Math.sqrt(l2));

        return scl((double)Math.cos(theta)).add(tx * dl, ty * dl, tz * dl).nor();
    }

    
    public String toString () {
        return "[" + x + ", " + y + ", " + z + "]";
    }


    
    public DoubleVector3 limit (double limit) {
        return limit2(limit * limit);
    }

    
    public DoubleVector3 limit2 (double limit2) {
        double len2 = len2();
        if (len2 > limit2) {
            scl(limit2 / len2);
        }
        return this;
    }

    
    public DoubleVector3 setLength ( double len ) {
        return setLength2( len * len );
    }

    
    public DoubleVector3 setLength2 ( double len2 ) {
        double oldLen2 = len2();
        return ( oldLen2 == 0 || oldLen2 == len2 )
                ? this
                : scl((double) Math.sqrt( len2 / oldLen2 ));
    }

    
    public DoubleVector3 clamp (double min, double max) {
        final double len2 = len2();
        if (len2 == 0f)
            return this;
        double max2 = max * max;
        if (len2 > max2)
            return scl((double)Math.sqrt(max2 / len2));
        double min2 = min * min;
        if (len2 < min2)
            return scl((double)Math.sqrt(min2 / len2));
        return this;
    }

    
    public int hashCode () {
        final int prime = 31;
        int result = 1;
        result = prime * result + NumberUtils.floatToIntBits((float) x);
        result = prime * result + NumberUtils.floatToIntBits((float) y);
        result = prime * result + NumberUtils.floatToIntBits((float) z);
        return result;
    }

    
    public boolean equals (Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        DoubleVector3 other = (DoubleVector3)obj;
        if (NumberUtils.floatToIntBits((float) x) != NumberUtils.floatToIntBits((float) other.x)) return false;
        if (NumberUtils.floatToIntBits((float) y) != NumberUtils.floatToIntBits((float) other.y)) return false;
        if (NumberUtils.floatToIntBits((float) z) != NumberUtils.floatToIntBits((float) other.z)) return false;
        return true;
    }

    
    public boolean epsilonEquals (final DoubleVector3 other, double epsilon) {
        if (other == null) return false;
        if (Math.abs(other.x - x) > epsilon) return false;
        if (Math.abs(other.y - y) > epsilon) return false;
        if (Math.abs(other.z - z) > epsilon) return false;
        return true;
    }

    /** Compares this vector with the other vector, using the supplied epsilon for fuzzy equality testing.
     * @return whether the vectors are the same. */
    public boolean epsilonEquals (double x, double y, double z, double epsilon) {
        if (Math.abs(x - this.x) > epsilon) return false;
        if (Math.abs(y - this.y) > epsilon) return false;
        if (Math.abs(z - this.z) > epsilon) return false;
        return true;
    }

    
    public DoubleVector3 setZero () {
        this.x = 0;
        this.y = 0;
        this.z = 0;
        return this;
    }

    public Vector3 toVector3() {
        return new Vector3((float) this.x, (float) this.y, (float) this.z);
    }
}
