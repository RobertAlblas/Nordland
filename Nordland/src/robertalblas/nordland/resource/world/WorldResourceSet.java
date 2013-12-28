package robertalblas.nordland.resource.world;

import robertalblas.nordland.collision.CollisionMap;
import robertalblas.nordland.entity.Player;
import robertalblas.nordland.entity.StaticEntity;
import robertalblas.nordland.entity.TerrainEntity;
import robertalblas.nordland.exception.XMLParseException;
import robertalblas.nordland.resource.ResourceSet;
import robertalblas.nordland.resource.graphics.Sprite;
import robertalblas.nordland.resource.graphics.SpriteManager;
import robertalblas.nordland.resource.graphics.SpriteSet;
import robertalblas.nordland.resource.sound.SoundManager;
import robertalblas.nordland.system.timer.TickTimerManager;
import robertalblas.nordland.system.xml.XMLImporter;
import robertalblas.nordland.system.xml.XMLNode;

public class WorldResourceSet extends ResourceSet {

	private TickTimerManager tickTimerManager;
	private SoundManager soundManager;
	private SpriteManager spriteManager;

	public WorldResourceSet(String file, TickTimerManager tickTimerManager, SoundManager soundManager, SpriteManager spriteManager) {
		super(file);
		this.tickTimerManager = tickTimerManager;
		this.soundManager = soundManager;
		this.spriteManager = spriteManager;
	}

	@Override
	public void load() throws XMLParseException {
		XMLImporter xmlImporter = new XMLImporter();
		XMLNode rootNode = xmlImporter.importXMLFile("/world/" + getFile() + ".xml", "worldresourceset");

		for (XMLNode worldNode : rootNode.getChildNodes()) {
			if (worldNode.getElementName().equals("world")) {
				WorldResource worldResource = new WorldResource(worldNode.getAttributeValue("name"));
				worldResource.setSoundManager(soundManager);
				worldResource.setSpriteManager(spriteManager);
				worldResource.setTickTimerManager(tickTimerManager);

				for (XMLNode worldDataNode : worldNode.getChildNodes()) {
					if (worldDataNode.getElementName().equals("worlddata")) {
						loadWorldData(worldResource, worldDataNode);
					} else {
						throw new XMLParseException("Unknown node " + worldDataNode.getElementName());
					}
				}
			} else {
				throw new XMLParseException("Unknown node " + worldNode.getElementName());
			}
		}
	}

	private void loadWorldData(WorldResource worldResource, XMLNode worldDataNode) throws XMLParseException {
		WorldData worldData = new WorldData();
		int width = Integer.parseInt(worldDataNode.getAttributeValue("width"));
		int height = Integer.parseInt(worldDataNode.getAttributeValue("width"));

		worldData.setCollisionMap(new CollisionMap(width, height));
		worldData.setWidth(width);
		worldData.setHeight(height);

		for (XMLNode entityNode : worldDataNode.getChildNodes()) {
			if (entityNode.getElementName().equals("entity")) {
				loadEntity(worldData, entityNode);
			} else if (entityNode.getElementName().equals("entityGroup")) {
				loadEntityGroup(worldData, entityNode);
			} else {
				throw new XMLParseException("Unknown node " + entityNode.getElementName());
			}
		}
	}

	private void loadEntityGroup(WorldData worldData, XMLNode entityNode) throws XMLParseException {
		// <entityGroup type="terrainEntity" spriteSet="tileset"
		// spite="ice" beginX="8" beginY="8" endX="152" endY="152" />

		int beginX = Integer.parseInt(entityNode.getAttributeValue("beginX"));
		int beginY = Integer.parseInt(entityNode.getAttributeValue("beginY"));
		int endX = Integer.parseInt(entityNode.getAttributeValue("endX"));
		int endY = Integer.parseInt(entityNode.getAttributeValue("endY"));

		SpriteSet spriteSet = (SpriteSet) spriteManager.getResourceSet(entityNode.getAttributeValue("spriteSet"));
		int height = ((Sprite) spriteSet.getResources().get(0)).getHeight();
		int width = ((Sprite) spriteSet.getResources().get(0)).getWidth();

		for (int x = beginX; x <= endX; x += width) {
			for (int y = beginY; y <= endY; y += height) {
				XMLNode groupedEntityNode = new XMLNode("entity");
				groupedEntityNode.addAttribute("type", entityNode.getAttributeValue("type"));
				groupedEntityNode.addAttribute("spriteSet", entityNode.getAttributeValue("spriteSet"));
				groupedEntityNode.addAttribute("sprite", entityNode.getAttributeValue("sprite"));
				groupedEntityNode.addAttribute("x", ""+x);
				groupedEntityNode.addAttribute("y", ""+y);
				
				loadEntity(worldData, groupedEntityNode);
			}
		}
	}

	private void loadEntity(WorldData worldData, XMLNode entityNode) throws XMLParseException {
		if (entityNode.getAttributeValue("type").equals("staticEntity")) {
			StaticEntity staticEntity = new StaticEntity(null, (SpriteSet) spriteManager.getResourceSet(entityNode
					.getAttributeValue("spriteSet")), Integer.parseInt(entityNode.getAttributeValue("x")),
					Integer.parseInt(entityNode.getAttributeValue("y")));
			staticEntity.setCurrentSprite(entityNode.getAttributeValue("sprite"));
			worldData.getEntities().add(staticEntity);
		} else if (entityNode.getAttributeValue("type").equals("player")) {
			Player player = new Player(null, (SpriteSet) spriteManager.getResourceSet(entityNode.getAttributeValue("spriteSet")),
					Integer.parseInt(entityNode.getAttributeValue("x")), Integer.parseInt(entityNode.getAttributeValue("y")));
			worldData.getEntities().add(player);
		} else if (entityNode.getAttributeValue("type").equals("terrainEntity")) {
			TerrainEntity terrainEntity = new TerrainEntity(null, (SpriteSet) spriteManager.getResourceSet(entityNode
					.getAttributeValue("spriteSet")), Integer.parseInt(entityNode.getAttributeValue("x")),
					Integer.parseInt(entityNode.getAttributeValue("y")));
			terrainEntity.setCurrentSprite(entityNode.getAttributeValue("sprite"));
			worldData.getEntities().add(terrainEntity);
		} else {
			throw new XMLParseException("Unknown type " + entityNode.getAttributeValue("type"));
		}
	}
}
