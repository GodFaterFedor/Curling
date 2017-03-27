package com.coursework.curling;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.coursework.curling.models.HashMap;
import com.coursework.curling.models.HashSet;
import com.coursework.curling.models.List;
import com.coursework.curling.models.TreeSet;
import com.coursework.curling.screens.GameScreen;
import com.sun.java_cup.internal.runtime.Symbol;

import java.util.Scanner;


public class Curling extends Game {

	public static SpriteBatch batch;

	private static Scanner scanner;
	private static List<Integer> list = new List<Integer>();
	private static TreeSet<Integer> tree = new TreeSet<Integer>();
	private static HashSet<Integer> set = new HashSet<Integer>();
	private static HashMap<String, Integer> map = new HashMap<String, Integer>();

	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new GameScreen());

	}

	private static void mapMenu() {
		while (true) {
			switch (menu(CollectionMenu.class)) {
				case ADD:
					System.out.println("Input key");
					scanner.nextLine();
					String string = scanner.nextLine();
					System.out.println("Input value");
					map.put(string, scanner.nextInt());
					break;
				case VIEW:
					System.out.println("Map: ");
					System.out.println(map);
					break;
				case REMOVE:
					System.out.println("Key");
					scanner.nextLine();
					map.remove(scanner.nextLine());
					break;
				case CLEAR:
					System.out.println("Cleared");
					map.clear();
					break;
				case BACK:
					return;
			}
		}
	}

	private static void setMenu() {
		while (true) {
			switch (menu(CollectionMenu.class)) {
				case ADD:
					System.out.println("Input new element");
					set.add(scanner.nextInt());
					break;
				case VIEW:
					System.out.println("Set: ");
					System.out.println(set);
					break;
				case REMOVE:
					System.out.println("Element");
					set.remove(scanner.nextInt());
					break;
				case CLEAR:
					System.out.println("Cleared");
					set.clear();
					break;
				case BACK:
					return;
			}
		}
	}

	private static void treeMenu() {
		while (true) {
			switch (menu(CollectionMenu.class)) {
				case ADD:
					System.out.println("Input new element");
					tree.add(scanner.nextInt());
					break;
				case VIEW:
					System.out.println("Tree: ");
					System.out.println(tree);
					break;
				case REMOVE:
					System.out.println("Element");
					tree.remove(scanner.nextInt());
					break;
				case CLEAR:
					System.out.println("Cleared");
					tree.clear();
					break;
				case BACK:
					return;
			}
		}
	}

	private static void listMenu() {
		while (true) {
			switch (menu(ListMenu.class)) {
				case ADD:
					System.out.println("Input new element");
					list.add(scanner.nextInt());
					break;
				case VIEW:
					System.out.println("List: ");
					System.out.println(list);
					break;
				case REMOVE:
					System.out.println("Index");
					list.remove(scanner.nextInt());
					break;
				case CLEAR:
					System.out.println("Cleared");
					list.clear();
					break;
				case SORT:
					System.out.println("Sorted");
					list.sort();
					System.out.println(list);
					break;
				case GET:
					System.out.println("Index: ");
					Integer element = list.get(scanner.nextInt());
					System.out.println(element);
					break;
				case BACK:
					return;
			}
		}
	}

	private static <E extends Enum<E>> E menu(Class<E> menu) {
		int input = 0;
		int i = 1;
		System.out.println();
		for (E point : menu.getEnumConstants()) {
			System.out.println(String.format("%s. %s", i++, point.name()));
		}
		try {
			input = scanner.nextInt() - 1;
		} catch (Exception ignored) {
		}
		System.out.println();
		return input >= 0 && input < menu.getEnumConstants().length ? menu.getEnumConstants()[input] : menu.getEnumConstants()[menu.getEnumConstants().length - 1];
	}


	private enum MainMenu {
		LIST, TREE_SET, HASH_SET, HASH_MAP, EXIT
	}

	private enum CollectionMenu {
		VIEW, ADD, REMOVE, CLEAR, BACK
	}

	private enum ListMenu {
		VIEW, ADD, REMOVE, SORT, GET, CLEAR, BACK
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {

		batch.dispose();
	}

	public SpriteBatch getBatch() {
		return batch;
	}



}
