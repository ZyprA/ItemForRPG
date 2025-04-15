---

# ItemForRPG プラグイン (Minecraft)

## 1. 概要
ItemForRPGはコンフィグファイルで設定したRPG用のアイテムを作り管理することができます。
右クリック時に発動させるコマンドも設定が可能です。

## 2. コマンド(/itemforrpg)

アイテムをゲット: /itemforrpg get <id>  
アイテムの情報を取得: /itemforrpg info <id>  
コンフィグをリロード: /itemforrpg reload

## 3. ファイル設定
### 1. configファイル
**(config.yml)**
キーfilesのリストは読み込みたいアイテムのファイルを参照してください
```yaml
files:
  - /items/test.yml
  - /items/test2.yml
```

### 2. アイテムの設定の例
config.ymlで指定したファイル(test.yml)はルートより全てのキーがアイテムとして登録されます。
以下の例では"supersword","helloworld"がIDとなり、それ下の各項目にパラメータを設定してください。
material項目で存在しないマテリアルを設定すると登録はできません

**(test.yml)**
```yaml
supersword:
  name: "§a§lスーパーソード"
  material: "DIAMOND_SWORD"
  lore:
    - §cThis is super sword
    - §6Legendary item
  cooldown: 10
  commands:
    player:
      - "say 会心の一撃!!"
    console:
      - "execute as %player% at %player% run execute as @e[distance=..10, type=!player] at @s run tp @s ~ ~10 ~"

hellworld:
  name: "§c§lハローワールドソード"
  material: "STONE_SWORD"
  lore:
    - §cThis is helloworld sword
    - §6Normal item
  cooldown: 2
  commands:
    player:
      - "say ハロー!!"
    console:
      - "say ハロー！"
```


## 4. 必要なプラグイン
- **[CommandAPI](https://github.com/CommandAPI/CommandAPI)**

## 5. 実行環境(推奨)
Spigot(Paper) 1.21.1

## 6. 開発者向け
### Document: [javadoc](https://zypra.github.io/ItemForRPG/)

## Appendix.
**追加した機能: クールダウン**
- cooldownを設定している場合、右クリックした際のクールダウンが発動する


---
