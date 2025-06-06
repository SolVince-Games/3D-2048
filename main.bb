Graphics3D 653, 653,32,2
SetBuffer BackBuffer()

camera = CreateCamera()

cam_x# = 0.0
cam_y# = 0.0
cam_z# = 0.0

cam_speed# = 0.001

light = CreateLight()

; Make Shapes
cylinder = CreateCylinder(32)
PositionEntity cylinder,-2,0,5

cube = CreateCube()
PositionEntity cube,3,0,5

sphere = CreateSphere(32)
PositionEntity sphere,0,0,5
; Make Shapes

While Not KeyDown(1)

PositionEntity camera,cam_x,cam_y,cam_z

RenderWorld
Flip

If KeyDown(29);Ctrl
    cam_speed = 0.01
Else
    cam_speed = 0.001
EndIf

If KeyDown(17);W
    cam_z = cam_z + cam_speed
EndIf

If KeyDown(31);S
    cam_z = cam_z - cam_speed
EndIf

If KeyDown(30);A
    cam_x = cam_x - cam_speed
EndIf

If KeyDown(32);D
    cam_x = cam_x + cam_speed
EndIf

If KeyDown(57);Space
    cam_y = cam_y + cam_speed
EndIf

If KeyDown(42);Shift
    cam_y = cam_y - cam_speed
EndIf

Wend
End