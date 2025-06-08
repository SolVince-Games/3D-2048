Graphics3D 653, 653, 32, 2
SetBuffer BackBuffer()

; Create Camera
camera = CreateCamera()

; Camera position and speed
cam_x# = 0.0
cam_y# = 0.0
cam_z# = 0.0
cam_speed# = 0.001

; Cube Grid Dimensions
length = 4
width = 4
depth = 4

; Create light
light = CreateLight()

; Create cubes in a 3D grid with 1-unit gaps (cube is 2x2x2, so 3 units apart)
For x = 0 To length - 1
    For y = 0 To width - 1
        For z = 0 To depth - 1
            cube = CreateCube()
            PositionEntity cube, x * 3, y * 3, z * 3
            EntityAlpha cube, 0.7 ; Make it kinda transparent
            EntityColor cube, Rnd(0, 255), Rnd(0, 255), Rnd(0, 255) ; Random color
        Next
    Next
Next

; Main Loop
While Not KeyDown(1)

    ; Position the camera
    PositionEntity camera, cam_x, cam_y, cam_z

    ; Render the scene
    RenderWorld

    ; Debug: Display key codes
    For i = 1 To 255
        If KeyDown(i)
            Text 100, 100, "Key pressed: " + i
        EndIf
    Next

    Flip

    ; Camera speed adjustment (Hold Ctrl to move faster)
    If KeyDown(29) ; Left Ctrl
        cam_speed = 0.05
    Else
        cam_speed = 0.01
    EndIf

    ; WASD + Space/Shift camera movement
    If KeyDown(17) Then cam_z = cam_z + cam_speed  ; W
    If KeyDown(31) Then cam_z = cam_z - cam_speed  ; S
    If KeyDown(30) Then cam_x = cam_x - cam_speed  ; A
    If KeyDown(32) Then cam_x = cam_x + cam_speed  ; D
    If KeyDown(57) Then cam_y = cam_y + cam_speed  ; Space
    If KeyDown(42) Then cam_y = cam_y - cam_speed  ; Left Shift

    If KeyDown(23) Then TurnEntity camera, -1, 0, 0  ; I
    If KeyDown(37) Then TurnEntity camera, 1, 0, 0   ; K
    If KeyDown(36) Then TurnEntity camera, 0, 1, 0   ; J
    If KeyDown(38) Then TurnEntity camera, 0, -1, 0  ; L

    If KeyDown(22) Then TurnEntity camera, 0, 0, 1   ; U
    If KeyDown(24) Then TurnEntity camera, 0, 0, -1  ; O

Wend

End