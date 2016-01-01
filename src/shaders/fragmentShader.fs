#version 400 core

in vec2 textureCoordinate;
in vec3 surfaceNormal;
in vec3 toLightVector;
in vec3 toCameraVector;

out vec4 color;

uniform sampler2D modelTexture;
uniform vec3 lightColor;
uniform float shineDamper;
uniform float reflectivity;

void main(void)
{
	// Diffuse lighting calculation
	vec3 unitNormal = normalize(surfaceNormal);
	vec3 unitLightVector = normalize(toLightVector);

	float nDotl = dot(unitNormal, unitLightVector);

	float brightness = max(nDotl, 0.2);
	vec3 diffuse = brightness * lightColor;

	// Specular lighting calculation
	vec3 unitVectorToCamera = normalize(toCameraVector);
	vec3 lightDirection = -unitLightVector;
	vec3 reflectedLightDirection = reflect(lightDirection, unitNormal);

	float specularFactor = dot(reflectedLightDirection, unitVectorToCamera);
	specularFactor = max(specularFactor, 0.0);
	float dampedSpecularFactor = pow(specularFactor, shineDamper);
	vec3 finalSpecular = dampedSpecularFactor * reflectivity * lightColor;

	color = vec4(diffuse, 1.0) * texture(modelTexture, textureCoordinate) + vec4(finalSpecular, 1.0);
}